package com.yaspeed.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yaspeed.common.DataGridResult;
import com.yaspeed.common.ReturnResult;
import com.yaspeed.web.pojo.RdsRole;
import com.yaspeed.web.pojo.RdsUser;
import com.yaspeed.web.pojo.RdsUserRole;
import com.yaspeed.web.service.RdsRoleService;
import com.yaspeed.web.service.RdsUserService;

@Controller
@RequestMapping("/rds_user")
public class RdsUserController {

	@Autowired
	private RdsUserService rdsUserService;
	
	@Autowired
	private RdsRoleService rdsRoleService;

	
	@RequestMapping("/rdsUserList")
	public String toPage() {
		return "rds_user/rds_user_list";
	}
	
	@RequestMapping("/findRdsUserList")
	@ResponseBody
	public DataGridResult findRdsUserList(
		@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
		@RequestParam(value = "rows", required = true, defaultValue = "10") Integer rows, RdsUser rdsUser)
		throws Exception {
	return rdsUserService.findRdsUserList(rdsUser, page, rows);

	}
	
	@RequestMapping("/setRdsRoleByUserId")
	public String setRdsRoleByUserId(Model model,String userId) throws Exception{
		List<RdsRole> rdsRoleList=rdsRoleService.findRdsRoleList();
		
		List<RdsUserRole> hRoleList=rdsUserService.findRdsRoleListByUserId(userId);
		model.addAttribute("rdsRoleList", rdsRoleList);
        Map<String,Boolean> hRoleListMap=new HashMap<>();
        if(hRoleList!=null){
           for(RdsUserRole rdsUserRole: hRoleList){
               hRoleListMap.put(rdsUserRole.getRoleId(),true);
           }
        }
		model.addAttribute("hRoleListMap", hRoleListMap);
		model.addAttribute("userId", userId);
		
		return "rds_user/rds_user_role";
	}
	
	@RequestMapping("/addUserRdsRole")
	@ResponseBody
	public ReturnResult addUserRdsRole(Model model,RdsUser rdsUser) throws Exception{
		return ReturnResult.success(rdsUserService.addUserRdsRoleByBatch(rdsUser));
	}
	
	
}
