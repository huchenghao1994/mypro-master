package com.yaspeed.web.dao;

import java.util.List;
import com.yaspeed.web.pojo.RdsMenu;

public interface RdsUserMenuMapper {
	
  public List<RdsMenu> findMenusListByUserId(String userId) throws Exception;

}