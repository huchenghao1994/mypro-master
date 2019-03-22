package com.yaspeed.web.service;

import java.util.List;

import com.yaspeed.common.DataGridResult;
import com.yaspeed.web.pojo.RdsMenu;
import com.yaspeed.web.pojo.ZtreeNodes;

public interface RdsMenuService {
	public DataGridResult findRdsMenuList(RdsMenu rdsMenu, Integer page, Integer rows) throws Exception;

	public int deleteRdsMenuList(RdsMenu rdsMenu) throws Exception;
	
	public int addRdsMenu(RdsMenu rdsMenu) throws Exception;
	
	public RdsMenu getRdsMenuByMenuId(String menuId) throws Exception;
	
	public int updateRdsMenu(RdsMenu rdsMenu) throws Exception;
	
	public List<ZtreeNodes> getRdsMenuByZtree() throws Exception;
	
}
