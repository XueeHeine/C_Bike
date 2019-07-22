package com.qdu.mapper;

import com.qdu.beans.CRoleModule;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CRoleModuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CRoleModule record);

    int insertSelective(CRoleModule record);

    CRoleModule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CRoleModule record);

    int updateByPrimaryKey(CRoleModule record);

    int deleteModuleByRole(CRoleModule cm);
    int batchDeleteModulesByRole(CRoleModule cm);
    List<CRoleModule> getBatchCMByModuleName(Map map);
    int addRolesModule(CRoleModule cm);
}