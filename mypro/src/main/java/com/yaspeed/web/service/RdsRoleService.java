package com.yaspeed.web.service;

import java.util.List;
import com.yaspeed.common.DataGridResult;
import com.yaspeed.web.pojo.RdsRole;
import com.yaspeed.web.pojo.RdsRoleMenu;

public interface RdsRoleService {
	public DataGridResult findRdsRoleList(RdsRole rdsRole, Integer page, Integer rows) throws Exception;

	public int deleteRdsRoleList(RdsRole rdsRole) throws Exception;
	
	public int addRdsRole(RdsRole rdsRole) throws Exception;

	public RdsRole getRdsRoleByRoleId(String roleId) throws Exception;
	
	public int updateRdsRole(RdsRole rdsRole) throws Exception;
	
	public List<RdsRoleMenu> getRdsRoleMenuByZtree(String RoleId) throws Exception;
	
	public List<RdsRole> findRdsRoleList() throws Exception;
	   
	
}
