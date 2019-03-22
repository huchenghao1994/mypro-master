package com.yaspeed.core.shiro;

import com.yaspeed.core.util.RequestUtil;
import com.yaspeed.web.pojo.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义拦截器,Session过期AJAX处理
 * 
 * @author Administrator
 *
 */
public class SessionExpiredFilter extends PathMatchingFilter {

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject = SecurityUtils.getSubject();
		ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
		if (activeUser == null) {
			if (RequestUtil.isAjaxRequest((HttpServletRequest) request)) {
				// ajax的sesson处理
				// 返回状态码
				onLoginFail(response);
				return false;
			} else {
				// 普通的处理，直接给到下一个拦截器
				return true;
			}
		}

		return true;
	}

	// session过期给403状态码
	private void onLoginFail(ServletResponse response) throws IOException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setStatus(401);
		httpResponse.getWriter().write("session超时了");
	}

}
