package com.qdu.mapper;

import com.qdu.beans.CModule;
import com.qdu.beans.CRole;
import com.qdu.beans.CRoleModule;
import com.qdu.beans.CUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CRole record);

    int insertSelective(CRole record);

    CRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CRole record);

    int updateByPrimaryKey(CRole record);
    List<CRole> queryRolesByUserId(CUser user);
    List<CModule> queryModulesByRoleId(CRole roleid);
    List<CRole> queryAllRoles();
    int addRoleByName(CRole role);
    int batchdeleteRolesById(@Param("ids")List<Long> ids);

}