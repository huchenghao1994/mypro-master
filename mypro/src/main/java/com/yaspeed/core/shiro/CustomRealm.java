package com.yaspeed.core.shiro;

import com.yaspeed.core.context.SpringContextHolder;
import com.yaspeed.core.util.StringUtil;
import com.yaspeed.web.cache.PermissionCacheProvider;
import com.yaspeed.web.pojo.ActiveUser;
import com.yaspeed.web.pojo.MenuItem;
import com.yaspeed.web.pojo.RdsMenu;
import com.yaspeed.web.pojo.RdsUser;
import com.yaspeed.web.service.RdsUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private Environment env;

	// 注入service
	@Autowired
	private RdsUserService rdsUserService;

	// realm的认证方法，从数据库查询用户信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String salt = env.getProperty("salt");
		// token是用户输入的用户名和密码
		// 第一步从token中取出用户名
		String userId = (String) token.getPrincipal();

		// 第二步：根据用户输入的userCode从数据库查询
		RdsUser rdsUser = null;
		try {
			rdsUser = rdsUserService.findRdsUserByUserId(userId);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// 如果查询不到返回null
		if (rdsUser == null) {//
			return null;
		}
		// 从数据库查询到密码
		String password = rdsUser.getPassword();
		// 盐
		// String salt = sysUser.getSalt();
		// 如果查询到返回认证信息AuthenticationInfo
		// activeUser就是用户身份信息
		ActiveUser activeUser = new ActiveUser();
		activeUser.setId(rdsUser.getId());
		activeUser.setUserId(rdsUser.getUserId());
		activeUser.setUsername(rdsUser.getUsername());
		// 根据用户id取出菜单
		List<MenuItem> menus = null;
		try {
			// 通过service取出菜单
			menus = rdsUserService.findMenuItemListByUserId(activeUser.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 将用户菜单 设置到activeUser
		activeUser.setMenus(menus);

		// 将activeUser设置simpleAuthenticationInfo
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser, password,
				ByteSource.Util.bytes(salt), this.getName());
		return simpleAuthenticationInfo;
	}

	// 用于授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 从 principals获取主身份信息
		// 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
		ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
		// 根据身份信息获取权限信息
		// 查到权限数据，返回授权信息(要包括 上边的permissions)
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// 单独定一个集合对象
		PermissionCacheProvider cacheProvider = SpringContextHolder.getBean("permissionCache");
		@SuppressWarnings("unchecked")
		List<String> cachePermissions = null;//(List<String>) cacheProvider.get("permissionCache");
		// 从数据库获取到权限数据
		List<RdsMenu> permissionList = null;
		if (null == cachePermissions) {
			// 首先从缓存 中获取，如果缓存中有
			try {
				permissionList = rdsUserService.findPercodeListByUserId(activeUser.getUserId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<String> dbPermissions = new ArrayList<String>();
			if (permissionList != null) {
				for (RdsMenu rdsMenu : permissionList) {
					// 将数据库中的权限标签 符放入集合
					if(StringUtil.isNotEmpty(rdsMenu.getPercode())) {
						dbPermissions.add(rdsMenu.getPercode());
					}
					
				}
				cacheProvider.put("permissionCache", (Serializable) dbPermissions);
			}
			// 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
			simpleAuthorizationInfo.addStringPermissions(dbPermissions);
			return simpleAuthorizationInfo;
		} else {
			simpleAuthorizationInfo.addStringPermissions(cachePermissions);
			return simpleAuthorizationInfo;
		}

	}

	// 清除缓存
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}

}
