package com.yaspeed.web.pojo;

import java.util.List;

/**
 * 用户身份信息，存入session 由于tomcat将session会序列化在本地硬盘上，所以使用Serializable接口
 * 
 * 
 */
public class ActiveUser implements java.io.Serializable {
	private static final long serialVersionUID = -1994439330199625244L;
	private Long id;
	private String userId;// 用户账号
	private String username;// 用户名称
	private List<MenuItem> menus;// 菜单
	private List<RdsMenu> permissions;// 权限
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<MenuItem> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuItem> menus) {
		this.menus = menus;
	}
	public List<RdsMenu> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<RdsMenu> permissions) {
		this.permissions = permissions;
	}
	
	
}
