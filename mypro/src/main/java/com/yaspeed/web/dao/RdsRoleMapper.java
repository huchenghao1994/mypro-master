package com.yaspeed.web.dao;

import com.yaspeed.web.pojo.RdsRole;
import com.yaspeed.web.pojo.RdsRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RdsRoleMapper {
    int countByExample(RdsRoleExample example);

    int deleteByExample(RdsRoleExample example);

    int deleteByPrimaryKey(String roleId);

    int insert(RdsRole record);

    int insertSelective(RdsRole record);

    List<RdsRole> selectByExample(RdsRoleExample example);

    RdsRole selectByPrimaryKey(String roleId);

    int updateByExampleSelective(@Param("record") RdsRole record, @Param("example") RdsRoleExample example);

    int updateByExample(@Param("record") RdsRole record, @Param("example") RdsRoleExample example);

    int updateByPrimaryKeySelective(RdsRole record);

    int updateByPrimaryKey(RdsRole record);
}