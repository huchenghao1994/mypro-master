package com.yaspeed.core.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

public class MyDefaultWebSessionManager extends DefaultWebSessionManager {
	@Override
	public boolean isServletContainerSessions() {
		return true;
	}
}
