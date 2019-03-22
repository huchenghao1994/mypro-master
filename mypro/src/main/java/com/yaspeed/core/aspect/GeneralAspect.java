package com.yaspeed.core.aspect;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yaspeed.common.DataGridResult;
import com.yaspeed.core.context.CoreMediator;
import com.yaspeed.core.util.DateUtils;
import com.yaspeed.web.pojo.ActiveUser;

@Aspect
@Component
public class GeneralAspect {
	Logger logger = LoggerFactory.getLogger(GeneralAspect.class);

	// 配置切点 及要传的参数
	@Pointcut("execution(* com.yaspeed.web.dao.*Mapper.update*(..))")
	public void updateAspect() {
	}

	// 配置切点 及要传的参数
	@Pointcut("execution(* com.yaspeed.web.dao.*Mapper.insert*(..))")
	public void insetAspect() {
	}

	// 配置切点 及要传的参数
	@Pointcut("execution(com.yaspeed.common.DataGridResult com.yaspeed.web.service.*Service.*(..))")
	public void selectAspect() {
	}

	@Around("insetAspect()")
	public Object insertAround(ProceedingJoinPoint pjp) throws Exception,Throwable {
		ActiveUser activeUser = CoreMediator.getInstance().getActiveUser();
		Object obj = pjp.getArgs()[0];
		try {
			Class<?> clazz = obj.getClass();
			Field[] files = clazz.getDeclaredFields();
			for (Field f : files) {
				f.setAccessible(true);
				if(f.getName().equals("createTime")) {
					f.set(obj,DateUtils.getNowTime());
				}else if(f.getName().equals("updateTime")) {
					f.set(obj,DateUtils.getNowTime());
				}
			}
		}catch (Exception e) {
			logger.debug(">>>>>>>>>>>>缺少set方法");
		}
		return pjp.proceed();
	}

	@Around("updateAspect()")
	public Object updateAround(ProceedingJoinPoint pjp) throws Exception,Throwable {
		ActiveUser activeUser = CoreMediator.getInstance().getActiveUser();
		Object obj = pjp.getArgs()[0];
		try {
			Class<?> clazz = obj.getClass();
			Field[] files = clazz.getDeclaredFields();
			for (Field f : files) {
				f.setAccessible(true);
				if(f.getName().equals("updateTime")) {
					f.set(obj,DateUtils.getNowTime());
				}
			}
		}catch (Exception e) {
			logger.debug(">>>>>>>>>>>>缺少set方法");
		}
		return pjp.proceed();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Around("selectAspect()")
	public Object selectAspect(ProceedingJoinPoint pjp) throws IOException, Throwable {
		logger.debug("当前执行》》》》》》》》》》》》》》:"+pjp.getSignature().getName());
		logger.debug("数据转换开始》》》》》》》》》》》》》》:"+DateUtils.getNowTime());
		Object o=pjp.proceed();
		if(o instanceof DataGridResult) {
			List list=((DataGridResult)o).getRows();
			//((DataGridResult) o).setRows(ConvertDataDictionary.getInstance().convertDataDictionary(list));
		}
		logger.debug("数据转换结束》》》》》》》》》》》》》》:"+DateUtils.getNowTime());
		return o;
	}
}
