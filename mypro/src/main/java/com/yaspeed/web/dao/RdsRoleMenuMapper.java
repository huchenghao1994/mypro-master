package com.yaspeed.web.dao;

import java.util.List;
import com.yaspeed.web.pojo.RdsRoleMenu;

public interface RdsRoleMenuMapper {
	
  public int insertByBatch(List<RdsRoleMenu> list) throws Exception;

  public int deleteByRoleId(String roleId) throws Exception;
  
  public List<RdsRoleMenu> selectByRoleId(String roleId) throws Exception;
}