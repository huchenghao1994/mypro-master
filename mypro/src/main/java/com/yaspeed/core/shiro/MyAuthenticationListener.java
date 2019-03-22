package com.yaspeed.core.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.PrincipalCollection;

public class MyAuthenticationListener implements AuthenticationListener {

	@Override
	public void onSuccess(AuthenticationToken token, AuthenticationInfo info) {
		System.err.println("登陆成功");
	}

	@Override
	public void onFailure(AuthenticationToken token, AuthenticationException ae) {
		System.err.println("登录失败");
	}

	@Override
	public void onLogout(PrincipalCollection principals) {
		System.err.println("注销");
	}

}
