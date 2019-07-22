package com.qdu.mapper;

import java.util.List;
import java.util.Map;

import com.qdu.beans.Bicycle;
import com.qdu.beans.Sup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BicycleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bicycle record);

    int insertSelective(Bicycle record);

    Bicycle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bicycle record);

    int updateByPrimaryKey(Bicycle record);

	List<Bicycle> getModelBySupid(@Param("supid") String supid);

	List<Bicycle> query(@Param("model") String model, @Param("supid") Integer supid);

	Bicycle getBcById(@Param("id") Integer id);

	int update(Bicycle bicycle);

	int remove(@Param("ids") List<Integer> ids);

	List<Bicycle> getModel();

	void jian(@Param("gno") String gno);

	void jia(@Param("gno") String gno);

	List<Bicycle> getBcs(@Param("ids") List<Integer> ids);


    List<Sup> getSup();


}