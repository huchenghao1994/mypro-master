package com.yaspeed.web.service;

import java.util.List;

import com.yaspeed.common.DataGridResult;
import com.yaspeed.web.pojo.MenuItem;
import com.yaspeed.web.pojo.RdsMenu;
import com.yaspeed.web.pojo.RdsUser;
import com.yaspeed.web.pojo.RdsUserRole;

public interface RdsUserService {
	public RdsUser findRdsUserByUserId(String userId) throws Exception;
	
	public List<MenuItem> findMenuItemListByUserId(String userId) throws Exception;
	
	public List<RdsMenu> findPercodeListByUserId(String userId) throws Exception;
	
	public DataGridResult findRdsUserList(RdsUser rdsUser, Integer page, Integer rows) throws Exception;
	
	public int addUserRdsRoleByBatch(RdsUser rdsUser) throws Exception;
	
	public List<RdsUserRole> findRdsRoleListByUserId(String userId) throws Exception;
	
}
