package com.yaspeed.web.pojo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 页面上显示的菜单项，每一个MenuItem对应一个节点 alt+shift+m
 * 
 * @author wyy
 */
public class MenuItem {

	private final String MODULE = "module";

	private Long id;// 0：根节点，否则是子节点
	private String menuId;//资源代码
	private String pid;//父级代码
	private String url;// 菜单的URL地址
	private String name;// 菜单名称
	private boolean open = false;// 是否展开
	private boolean checked;// true:勾选
	private List<MenuItem> children;// 子节点
	private MenuType type = MenuType.module;// module：模块 ,page：页面 ,button：功能
	private String icon;// 节点图标

	/**
	 * 指定菜单的功能类型
	 * 
	 * @param menu
	 */
	public void setMenuType(RdsMenu menu) {
		if (menu != null && StringUtils.isNotEmpty(menu.getMenuType())) {

			if (MODULE.equals(menu.getMenuType())) {
				this.setType(MenuType.module);
			} else if (menu.getMenuType().equals("page")) {
				this.setType(MenuType.page);
			} else if (menu.getMenuType().equals("button")) {
				this.setType(MenuType.button);
			}
		}
	}

	/**
	 * 判断是否是非按钮的功能
	 * 
	 * @return
	 */
	public boolean isButton() {
		if (this.type != null && this.type.equals(MenuType.button)) {
			return true;
		}
		return false;
	}

	public boolean isRootMenu() {
		return pid.equals("0");
	}

	public void addClild(MenuItem item) {
		if (children == null) {
			children = new ArrayList<MenuItem>();
		}
		children.add(item);
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<MenuItem> getChildren() {
		return children;
	}

	public void setChildren(List<MenuItem> children) {
		this.children = children;
	}

	public MenuItem(String name, List<MenuItem> children) {
		super();
		this.name = name;
		this.children = children;
	}

	public MenuItem() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public MenuType getType() {
		return type;
	}

	public void setType(MenuType type) {
		this.type = type;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
