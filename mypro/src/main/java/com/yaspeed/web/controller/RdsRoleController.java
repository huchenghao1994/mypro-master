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
import com.yaspeed.web.pojo.RdsRole;
import com.yaspeed.web.service.RdsRoleService;

/**
 * @description 角色管理
 * @author huchenghao
 * @version 1.0
 */
@Controller
@RequestMapping("/rds_role")
public class RdsRoleController {

	@Autowired
	private RdsRoleService rdsRoleService;

	/**
	 * @description 首次加载列表页面
	 * @return String
	 */
	@RequestMapping("/rdsRoleList")
	@RequiresPermissions("rdsRole:list")
	public String toPage() {
		return "rds_role/rds_role_list";
	}

	/**
	 * @description 跳转增加页面
	 * @param
	 * @return String
	 */
	@RequestMapping("/toAddRdsRole")
	public String toAddRdsRole() {
		return "rds_role/rds_role_add";
	}

	/**
	 * @description 跳转更新页面
	 * @param model id
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping("/toUpdateRdsRole")
	public String toUpdateRdsRole(Model model,String roleId) throws Exception {
		RdsRole rdsRole = rdsRoleService.getRdsRoleByRoleId(roleId);
		model.addAttribute(rdsRole);
		return "rds_role/rds_role_update";
	}
	
	/**
	 * @description 增加角色信息
	 * @param rdsRole
	 * @return DataGridResult
	 * @throws Exception
	 */
	@RequestMapping("/addRdsRole")
	@RequiresPermissions("rdsRole:add")
	@ResponseBody
	public ReturnResult addRdsUser(@Valid RdsRole rdsRole) throws Exception {
		return ReturnResult.success(rdsRoleService.addRdsRole(rdsRole));
	}
	
	/**
	 * @description 更新角色信息
	 * @param
	 * @return DataGridResult
	 * @throws Exception
	 */
	@RequestMapping("/updateRdsRole")
	@RequiresPermissions("rdsRole:update")
	@ResponseBody
	public ReturnResult updateRdsRole(RdsRole rdsRole) throws Exception {
		return ReturnResult.success(rdsRoleService.updateRdsRole(rdsRole));
	}

	/**
	 * @description 分页查询用户信息
	 * @param page rows rdsUser
	 * @return DataGridResult
	 * @throws Exception
	 */
	@RequestMapping("/queryRdsRoleList")
	@RequiresPermissions("rdsRole:search")
	@ResponseBody
	public DataGridResult queryRdsRoleList(
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = true, defaultValue = "10") Integer rows, RdsRole rdsRole)
			throws Exception {
		return rdsRoleService.findRdsRoleList(rdsRole, page, rows);
	}

	/**
	 * @description 删除用户信息
	 * @param
	 * @return ReturnResult
	 * @throws Exception
	 */
	@RequestMapping("/deleteRdsRoleList")
	@RequiresPermissions("rdsRole:delete")
	@ResponseBody
	public ReturnResult deleteRdsRoleList(RdsRole rdsRole) throws Exception {
		return ReturnResult.success(rdsRoleService.deleteRdsRoleList(rdsRole));
	}

	/**
	 * @description 树状查询
	 * @param
	 * @return ReturnResult
	 * @throws Exception
	 */
	@RequestMapping("/getRdsRoleMenuByZtree")
	@ResponseBody
	public ReturnResult getRdsMenuByZtree(String roleId) throws Exception {
		return ReturnResult.success(rdsRoleService.getRdsRoleMenuByZtree(roleId));
	}
}
