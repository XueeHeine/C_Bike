package com.qdu.mapper;

import com.qdu.beans.CUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CUser record);

    CUser getUserByUS(CUser user);

    CUser getUserByName(CUser user);

    int insertSelective(CUser record);

    CUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CUser record);

    int updateByPrimaryKey(CUser record);
    List<CUser> queryAll();
}