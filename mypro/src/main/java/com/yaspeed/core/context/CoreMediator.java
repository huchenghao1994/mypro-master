package com.yaspeed.core.context;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import com.yaspeed.web.pojo.ActiveUser;

public class CoreMediator {
	// 实现成为单例
	private static CoreMediator mediator = new CoreMediator();

	private CoreMediator() {
	}

	public static CoreMediator getInstance() {
		return mediator;
	}
	/**
	 * 获取登录人信息
	 * 
	 * @return
	 */
	public ActiveUser getActiveUser() {
		Subject subject = SecurityUtils.getSubject();
		return (ActiveUser) subject.getPrincipal();
	}

}
