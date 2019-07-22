package com.qdu.mapper;

import com.qdu.beans.CModule;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CModuleMapper {
    int insert(CModule record);

    int insertSelective(CModule record);
    List<CModule> queryAllModules();

}