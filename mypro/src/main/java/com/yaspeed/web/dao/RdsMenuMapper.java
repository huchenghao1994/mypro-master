package com.yaspeed.web.dao;

import com.yaspeed.web.pojo.RdsMenu;
import com.yaspeed.web.pojo.RdsMenuExample;
import com.yaspeed.web.pojo.ZtreeNodes;

import java.util.List;

import org.apache.ibatis.annotations.Param;
public interface RdsMenuMapper {
    int countByExample(RdsMenuExample example);

    int deleteByExample(RdsMenuExample example);

    int deleteByPrimaryKey(String menuId);

    int insert(RdsMenu record);

    int insertSelective(RdsMenu record);

    List<RdsMenu> selectByExample(RdsMenuExample example);

    RdsMenu selectByPrimaryKey(String menuId);

    int updateByExampleSelective(@Param("record") RdsMenu record, @Param("example") RdsMenuExample example);

    int updateByExample(@Param("record") RdsMenu record, @Param("example") RdsMenuExample example);

    int updateByPrimaryKeySelective(RdsMenu record);

    int updateByPrimaryKey(RdsMenu record);
    
    List<ZtreeNodes> getRdsMenuByZtree();
}