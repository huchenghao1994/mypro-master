package com.yaspeed.web.dao;

import com.yaspeed.web.pojo.RdsUserRole;
import com.yaspeed.web.pojo.RdsUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RdsUserRoleMapper {
    int countByExample(RdsUserRoleExample example);

    int deleteByExample(RdsUserRoleExample example);

    int deleteByPrimaryKey(String userId);

    int insert(RdsUserRole record);

    int insertSelective(RdsUserRole record);

    List<RdsUserRole> selectByExample(RdsUserRoleExample example);

    RdsUserRole selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") RdsUserRole record, @Param("example") RdsUserRoleExample example);

    int updateByExample(@Param("record") RdsUserRole record, @Param("example") RdsUserRoleExample example);

    int updateByPrimaryKeySelective(RdsUserRole record);

    int updateByPrimaryKey(RdsUserRole record);
    
    int insertUserRdsRoleByBatch(List<RdsUserRole> list);
}