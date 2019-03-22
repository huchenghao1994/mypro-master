package com.yaspeed.core.shiro;

import com.yaspeed.web.pojo.ActiveUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	Logger logger = LoggerFactory.getLogger(MyFormAuthenticationFilter.class);

	private List<String> exCludeUrl;

	public List<String> getExCludeUrl() {
		return exCludeUrl;
	}

	public void setExCludeUrl(List<String> exCludeUrl) {
		this.exCludeUrl = exCludeUrl;
	}

	/**
	 * 指定的url传递进去，这样就实现了跳转到指定的页面；
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {

		// 处理有些链接不需要跳转到上一个链接
		if (WebUtils.getSavedRequest(request) != null
				&& exCludeUrl.contains(WebUtils.getSavedRequest(request).getRequestUrl())) {
			WebUtils.getAndClearSavedRequest(request);// 清理了session中保存的请求信息
			WebUtils.redirectToSavedRequest(request, response, getSuccessUrl());
			return false;
		}
		return super.onLoginSuccess(token, subject, request, response);
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
//		// 01 开启 02关闭
//		if (CoreMediator.getInstance().getDics("public3007").get("open").equals("01")) {
//			try {
//				LoginLog log = new LoginLog();
//				log.setIsException("1");
//				log.setExceptionInfo(ExceptionPrintUtil.printException(e));
//				LoginLogService loginService = SpringContextHolder.getBean(LoginLogService.class);
//				loginService.insert(packageLoginLog(log));
//			} catch (Exception e1) {
//				logger.error("保存登录日志出错:");
//			}
		// }
		return super.onLoginFailure(token, e, request, response);

	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				// 本次用户登陆账号
				Subject subject = this.getSubject(request, response);
				String url=getSuccessUrl();
				// 之前登陆的用户
				ActiveUser user = (ActiveUser) subject.getPrincipal();
				// 解决如果已经登录，然后再次到http://localhost:8089/login 重新登录的情况
				if (null != user) {
					try {
						WebUtils.redirectToSavedRequest(request, response, getSuccessUrl());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return super.isAccessAllowed(request, response, mappedValue);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		HttpServletRequest httpServletRequest=(HttpServletRequest)request;

		String sessionCode = (String) httpServletRequest.getSession().getAttribute("validateCode");
		String inputCode = httpServletRequest.getParameter("randomcode");


		if(sessionCode!=null&&inputCode!=null) {
			if(!sessionCode.toLowerCase().equals(inputCode.toLowerCase())){
				httpServletRequest.setAttribute("shiroLoginFailure", "randomCodeError");
				//拒绝访问不在进行身份验证
				return true;
			}
		}
        return super.onAccessDenied(request, response, mappedValue);


    }
}
