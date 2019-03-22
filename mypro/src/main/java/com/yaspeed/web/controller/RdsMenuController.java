package com.yaspeed.web.controller;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yaspeed.common.DataGridResult;
import com.yaspeed.common.ReturnResult;
import com.yaspeed.web.pojo.RdsMenu;
import com.yaspeed.web.service.RdsMenuService;

/**
 * @description 菜单管理
 * @author huchenghao
 * @version 1.0
 */
@Controller
@RequestMapping("/rds_menu")
public class RdsMenuController {

	@Autowired
	private RdsMenuService rdsMenuService;

	@RequestMapping("/rdsMenuList")
	public String toPage(Model model) {
		return "rds_menu/rds_menu_list";
	}

	/**
	 * @description 跳转增加页面
	 * @param model id
	 * @return String
	 */
	@RequestMapping("/toAddRdsMenu")
    @RequiresPermissions("rdsMenu:add")
	public String toAddRdsMenu(Model model, String pid) {
		model.addAttribute("pid", pid);
		return "rds_menu/rds_menu_add";
	}

	/**
	 * @description 跳转更新页面
	 * @param model id
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping("/toUpdateRdsMenu")
	public String toUpdateRdsMenu(Model model, String menuId) throws Exception {
		RdsMenu rdsMenu = rdsMenuService.getRdsMenuByMenuId(menuId);
		model.addAttribute(rdsMenu);
		return "rds_menu/rds_menu_update";
	}

	@RequestMapping("/addRdsMenu")
	@RequiresPermissions("rdsMenu:add")
	@ResponseBody
	public ReturnResult addRdsMenu(@Valid RdsMenu rdsMenu) throws Exception {
		return ReturnResult.success(rdsMenuService.addRdsMenu(rdsMenu));
	}

	/**
	 * @description 更新资源信息
	 * @param  rdsMenu
	 * @return DataGridResult
	 * @throws Exception
	 */
	@RequestMapping("/updateRdsMenu")
	@RequiresPermissions("rdsMenu:update")
	@ResponseBody
	public ReturnResult updateRdsMenu(RdsMenu rdsMenu) throws Exception {
		return ReturnResult.success(rdsMenuService.updateRdsMenu(rdsMenu));
	}

	/**
	 * @description 分页查询用户信息
	 * @param page rows RdsMenu
	 * @return DataGridResult
	 * @throws Exception
	 */
	@RequestMapping("/queryRdsMenuList")
	@RequiresPermissions("rdsMenu:search")
	@ResponseBody
	public DataGridResult queryRdsMenuList(
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = true, defaultValue = "10") Integer rows, RdsMenu rdsMenu)
			throws Exception {
		return rdsMenuService.findRdsMenuList(rdsMenu, page, rows);
	}

	/**
	 * @description 删除用户信息
	 * @param rdsMenu
	 * @return ReturnResult
	 * @throws Exception
	 */
	@RequestMapping("/deleteRdsMenuList")
	@RequiresPermissions("rdsMenu:delete")
	@ResponseBody
	public ReturnResult deleteRdsMenuList(RdsMenu rdsMenu) throws Exception {
		return ReturnResult.success(rdsMenuService.deleteRdsMenuList(rdsMenu));
	}

	/**
	 * @description 树状查询
	 * @param
	 * @return ReturnResult
	 * @throws Exception
	 */
	@RequestMapping("/getRdsMenuByZtree")
	@ResponseBody
	public ReturnResult getRdsMenuByZtree() throws Exception {
		return ReturnResult.success(rdsMenuService.getRdsMenuByZtree());
	}

}
