package com.yaspeed.web.interceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.yaspeed.core.util.WebUtil;
import com.yaspeed.web.pojo.ActiveUser;

/**
 * 在Action的方法返回前加上当前登录的用户信息
 * 
 * @author Administrator
 *
 */
public class SessionUserInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(SessionUserInterceptor.class);
	private List<String> exceptUrls;

	public List<String> getExceptUrls() {
		return exceptUrls;
	}

	public void setExceptUrls(List<String> exceptUrls) {
		this.exceptUrls = exceptUrls;
	}

	// 在执行handler之前来执行的
	// 用于用户认证校验、用户权限校验
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	// 在执行handler返回modelAndView之前来执行
	// 如果需要向页面提供一些公用 的数据或配置一些视图信息，使用此方法实现 从modelAndView入手
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (HandlerMethod.class.equals(handler.getClass())) {
			StringBuilder sb = new StringBuilder(1000);
			sb.append("\r\n ##-----------------------------调用信息--------------------------##\n");
			HandlerMethod h = (HandlerMethod) handler;
			sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
			sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
			sb.append("Params    : ").append(getParamString(request.getParameterMap())).append("\n");
			sb.append("URI       : ").append(request.getRequestURI()).append("\n");
			logger.info(sb.toString());
		}
		Subject subject = SecurityUtils.getSubject();
		if (null != subject) {
			if (!WebUtil.isAJAX(request)) {
				// 取身份信息
				ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
				if (null != activeUser && null != modelAndView) {
					modelAndView.addObject("activeUser", activeUser);
				}
			}
		}
	}

	// 执行handler之后执行此方法
	// 作系统 统一异常处理，进行方法执行性能监控，在preHandle中设置一个时间点，在afterCompletion设置一个时间，两个时间点的差就是执行时长
	// 实现 系统 统一日志记录
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	private String getParamString(Map<String, String[]> map) {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String[]> e : map.entrySet()) {
			sb.append(e.getKey()).append("=");
			String[] value = e.getValue();
			if (value != null && value.length == 1) {
				sb.append(value[0]).append("\t");
			} else {
				sb.append(Arrays.toString(value)).append("\t");
			}
		}
		return sb.toString();
	}

	private Throwable getRootCause(Throwable throwable) {
		while (throwable.getCause() != null) {
			throwable = throwable.getCause();
		}
		return throwable;
	}
}
