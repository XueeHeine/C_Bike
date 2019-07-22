package com.qdu.mapper;

import com.qdu.beans.CUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CUserRole record);

    int insertSelective(CUserRole record);

    CUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CUserRole record);

    int updateByPrimaryKey(CUserRole record);
    int deleteUsersRoleByUserid(CUserRole ur);
    int  addUsersRole(CUserRole ur);
    int deleteUsersAllRoleByUserid(CUserRole ur);
}