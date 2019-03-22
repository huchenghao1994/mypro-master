package com.yaspeed.core.interceptor;//package com.yaspeed.core.interceptor;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.defaults.DefaultSqlSession.StrictMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.yaspeed.core.util.IPUtil;
import com.yaspeed.core.util.RequestHolder;
import com.yaspeed.core.util.UserAgentUtils;
import com.yaspeed.web.pojo.OperationLog;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * Sql执行时间记录拦截器
 */
@Intercepts({ @org.apache.ibatis.plugin.Signature(type = Executor.class, method = "update", args = {
		MappedStatement.class, Object.class }) })

public class SqlCostInterceptor implements Interceptor {
	private static final Logger logger = LoggerFactory.getLogger(SqlCostInterceptor.class);

	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		// 01 开启 02关闭
//		if (CoreMediator.getInstance().getDics("public3006").get("open").equals("02")) {
//			return invocation.proceed();
//		}
		OperationLog operationLog = new OperationLog();
		// 获取类名
		// 方法名
		// sql语句
		// 参数
		// 影响的行数
		// 执行时间 时分秒
		// 执行人所在机构
		// 执行人
		HttpServletRequest request = RequestHolder.getRequest();
		UserAgent userAgent = UserAgentUtils.getUserAgent(request);
		operationLog.setVisitIp(IPUtil.getIP(request));
		operationLog.setDeviceName(userAgent.getOperatingSystem().getName());
		operationLog.setBrowserName(userAgent.getBrowser().getName());
		operationLog.setUserAgent(request.getHeader("User-Agent"));
		String requestUri = request.getRequestURI();
		operationLog.setControlUri(requestUri.substring(requestUri.indexOf("md") + 3));
		final Object[] args = invocation.getArgs();
		MappedStatement mappedStatement = (MappedStatement) args[0];
		Object parameter = new Object();
		if (args.length > 1) {
			parameter = args[1];
//			if (parameter instanceof OperationLog || parameter instanceof LoginLog) {
//				// 若是日志对象 则直接跳过
//				return invocation.proceed();
//			}
		}
		String namespace = mappedStatement.getId();
		String className = namespace.substring(0, namespace.lastIndexOf("."));
		operationLog.setClassName(className.substring(className.lastIndexOf(".") + 1));
		String methodName = namespace.substring(namespace.lastIndexOf(".") + 1, namespace.length());
		operationLog.setMethodName(methodName);

		operationLog.setParaDesc(JSON.toJSONString(parameter));
		Configuration configuration = mappedStatement.getConfiguration();
		Object target = invocation.getTarget();
		StatementHandler handler = configuration.newStatementHandler((Executor) target, mappedStatement, parameter,
				RowBounds.DEFAULT, null, null);
		BoundSql boundSql = handler.getBoundSql();
		boundSql.getParameterMappings();
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();
		// 格式化Sql语句，去除换行符，替换参数
		String sql = formatSql(boundSql.getSql(), parameterObject, parameterMappingList);
		// 记录SQL
		operationLog.setSqlDesc(sql);
		// 执行真正的方法
		Object result = invocation.proceed();
		operationLog.setInfluenceRowNumber(Integer.valueOf(Integer.parseInt(result.toString())));
		//operationLog.setCreateDate(DateUtil.getCurrentDate());
		//operationLog.setCreateTime(DateUtil.getCurrentTime());
		//operationLog.setCreateTeller(CoreMediator.getInstance().getActiveUser().getTellerId());
		//operationLog.setCreateUnit(CoreMediator.getInstance().getActiveUser().getUnitId());
		// 获取insertSqlLog方法
		mappedStatement = mappedStatement.getConfiguration()
				.getMappedStatement("com.yaspeed.web.dao.OperationLogMapper.insertSelective");
		// 替换当前的参数为新的ms
		args[0] = mappedStatement;
		// insertSqlLog 方法的参数为 log
		args[1] = operationLog;
		try {
			invocation.proceed();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("插入日志失败！！！");
		}

		// 返回真正方法执行的结果
		return result;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

	@SuppressWarnings("unchecked")
	private String formatSql(String sql, Object parameterObject, List<ParameterMapping> parameterMappingList) {
		// 输入sql字符串空判断
		if (sql == null || sql.length() == 0) {
			return "";
		}
		// 美化sql
		sql = beautifySql(sql);

		// 不传参数的场景，直接把Sql美化一下返回出去
		if (parameterObject == null || parameterMappingList == null || parameterMappingList.size() == 0) {
			return sql;
		}

		// 定义一个没有替换过占位符的sql，用于出异常时返回
		String sqlWithoutReplacePlaceholder = sql;
		try {
			if (parameterMappingList != null) {
				Class<?> parameterObjectClass = parameterObject.getClass();

				// 如果参数是StrictMap且Value类型为Collection，获取key="list"的属性，这里主要是为了处理<foreach>循环时传入List这种参数的占位符替换
				// 例如select * from xxx where id in <foreach collection="list">...</foreach>
				if (isStrictMap(parameterObjectClass)) {
					StrictMap<Collection<?>> strictMap = (StrictMap<Collection<?>>) parameterObject;

					if (isList(strictMap.get("list").getClass())) {
						sql = handleListParameter(sql, strictMap.get("list"));
					}
				} else if (isMap(parameterObjectClass)) {
					// 如果参数是Map则直接强转，通过map.get(key)方法获取真正的属性值
					// 这里主要是为了处理<insert>、<delete>、<update>、<select>时传入parameterType为map的场景
					Map<?, ?> paramMap = (Map<?, ?>) parameterObject;
					sql = handleMapParameter(sql, paramMap, parameterMappingList);
				} else {
					// 通用场景，比如传的是一个自定义的对象或者八种基本数据类型之一或者String
					sql = handleCommonParameter(sql, parameterMappingList, parameterObjectClass, parameterObject);
				}
			}
		} catch (Exception e) {
			// 占位符替换过程中出现异常，则返回没有替换过占位符但是格式美化过的sql，这样至少保证sql语句比BoundSql中的sql更好看
			return sqlWithoutReplacePlaceholder;
		}
		return sql;
	}

	/**
	 * 美化Sql
	 */
	private String beautifySql(String sql) {
		sql = sql.replaceAll("[\\s\n ]+", " ");
		return sql;
	}

	/**
	 * 处理参数为List的场景
	 */
	private String handleListParameter(String sql, Collection<?> col) {
		if (col != null && col.size() != 0) {
			for (Object obj : col) {
				String value = null;
				Class<?> objClass = obj.getClass();
				// 只处理基本数据类型、基本数据类型的包装类、String这三种
				// 如果是复合类型也是可以的，不过复杂点且这种场景较少，写代码的时候要判断一下要拿到的是复合类型中的哪个属性
				if (isPrimitiveOrPrimitiveWrapper(objClass)) {
					value = obj.toString();
				} else if (objClass.isAssignableFrom(String.class)) {
					value = "\"" + obj.toString() + "\"";
				}

				sql = sql.replaceFirst("\\?", value);
			}
		}

		return sql;
	}

	/**
	 * 处理参数为Map的场景
	 */
	private String handleMapParameter(String sql, Map<?, ?> paramMap, List<ParameterMapping> parameterMappingList) {
		for (ParameterMapping parameterMapping : parameterMappingList) {
			Object propertyName = parameterMapping.getProperty();
			Object propertyValue = paramMap.get(propertyName);
			if (propertyValue != null) {
				if (propertyValue.getClass().isAssignableFrom(String.class)) {
					propertyValue = "\"" + propertyValue + "\"";
				}

				sql = sql.replaceFirst("\\?", propertyValue.toString());
			}
		}

		return sql;
	}

	/**
	 * 处理通用的场景
	 */
	private String handleCommonParameter(String sql, List<ParameterMapping> parameterMappingList,
			Class<?> parameterObjectClass, Object parameterObject) throws Exception {
		for (ParameterMapping parameterMapping : parameterMappingList) {
			String propertyValue = null;
			// 基本数据类型或者基本数据类型的包装类，直接toString即可获取其真正的参数值，其余直接取paramterMapping中的property属性即可
			if (isPrimitiveOrPrimitiveWrapper(parameterObjectClass)) {
				propertyValue = parameterObject.toString();
			} else {
				String propertyName = parameterMapping.getProperty();

				Field field = parameterObjectClass.getDeclaredField(propertyName);
				// 要获取Field中的属性值，这里必须将私有属性的accessible设置为true
				field.setAccessible(true);
				propertyValue = String.valueOf(field.get(parameterObject));
				if (parameterMapping.getJavaType().isAssignableFrom(String.class)) {
					propertyValue = "\"" + propertyValue + "\"";
				}
			}

			sql = sql.replaceFirst("\\?", propertyValue);
		}

		return sql;
	}

	/**
	 * 是否基本数据类型或者基本数据类型的包装类
	 */
	private boolean isPrimitiveOrPrimitiveWrapper(Class<?> parameterObjectClass) {
		return parameterObjectClass.isPrimitive() || (parameterObjectClass.isAssignableFrom(Byte.class)
				|| parameterObjectClass.isAssignableFrom(Short.class)
				|| parameterObjectClass.isAssignableFrom(Integer.class)
				|| parameterObjectClass.isAssignableFrom(Long.class)
				|| parameterObjectClass.isAssignableFrom(Double.class)
				|| parameterObjectClass.isAssignableFrom(Float.class)
				|| parameterObjectClass.isAssignableFrom(Character.class)
				|| parameterObjectClass.isAssignableFrom(Boolean.class));
	}

	/**
	 * 是否DefaultSqlSession的内部类StrictMap
	 */
	private boolean isStrictMap(Class<?> parameterObjectClass) {
		return parameterObjectClass.isAssignableFrom(StrictMap.class);
	}

	/**
	 * 是否List的实现类
	 */
	private boolean isList(Class<?> clazz) {
		Class<?>[] interfaceClasses = clazz.getInterfaces();
		for (Class<?> interfaceClass : interfaceClasses) {
			if (interfaceClass.isAssignableFrom(List.class)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 是否Map的实现类
	 */
	private boolean isMap(Class<?> parameterObjectClass) {
		Class<?>[] interfaceClasses = parameterObjectClass.getInterfaces();
		for (Class<?> interfaceClass : interfaceClasses) {
			if (interfaceClass.isAssignableFrom(Map.class)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		String str = "/md/rule_template_define/udpateRuleTemplateDefine";

		System.err.println(str.substring(str.lastIndexOf("md") + 3));
	}

}