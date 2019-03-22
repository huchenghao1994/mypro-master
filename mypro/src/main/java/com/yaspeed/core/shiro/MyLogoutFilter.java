package com.yaspeed.core.shiro;

import com.yaspeed.core.util.RequestHolder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyLogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		System.err.println("进入注销中");
		RequestHolder.getSession().removeAttribute("activeUser");
		System.err.println(RequestHolder.getSession().getAttribute("activeUser"));
		return super.preHandle(request, response);
	}
}
