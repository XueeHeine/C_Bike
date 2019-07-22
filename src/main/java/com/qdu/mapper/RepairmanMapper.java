package com.qdu.mapper;


import com.qdu.beans.Repairman;
import com.qdu.beans.Sup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Alpha-LGC on 2018/11/16.
 */
@Mapper
@Repository
public interface RepairmanMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Repairman record);

    int insertSelective(Repairman record);

    Repairman selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Repairman record);

    int updateByPrimaryKey(Repairman record);

    List<Repairman> getModelBySupid(@Param("supid") String supid);

    List<Repairman> query(@Param("model") String model, @Param("supid") Integer supid);

    Repairman getBcById(@Param("id") Integer id);

    int update(Repairman Repairman);

    int remove(@Param("ids") List<Integer> ids);

    List<Repairman> getModel();

    void jian(@Param("gno") String gno);

    void jia(@Param("gno") String gno);

    List<Repairman> getBcs(@Param("ids") List<Integer> ids);


    List<Sup> getSup();


}
