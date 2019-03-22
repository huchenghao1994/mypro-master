package com.yaspeed.web.dao;

import com.yaspeed.web.pojo.RdsUser;
import com.yaspeed.web.pojo.RdsUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RdsUserMapper {
    int countByExample(RdsUserExample example);

    int deleteByExample(RdsUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(RdsUser record);

    int insertSelective(RdsUser record);

    List<RdsUser> selectByExample(RdsUserExample example);

    RdsUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") RdsUser record, @Param("example") RdsUserExample example);

    int updateByExample(@Param("record") RdsUser record, @Param("example") RdsUserExample example);

    int updateByPrimaryKeySelective(RdsUser record);

    int updateByPrimaryKey(RdsUser record);
    
}