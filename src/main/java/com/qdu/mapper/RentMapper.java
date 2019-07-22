package com.qdu.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.qdu.beans.Bicycle;
import com.qdu.beans.Rentt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface RentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Rentt record);

    int insertSelective(Rentt record);

    Rentt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rentt record);

    int updateByPrimaryKey(Rentt record);

	int getMaxId();

	List<Rentt> query();

	Rentt getById(@Param("id") Integer id);

	int update(Rentt rent);

	List<Date> gtime(@Param("ftime") Date ftime, @Param("ltime") Date ltime);

	int count(@Param("gtime") Date d, @Param("category") int i);

    List<Map<String,Object>> getListByIdTime(@Param("gno") String gno, @Param("time") String time);

    Map<String,Object> getProfit(Bicycle b1);

    List<Map<String,Object>> getDayListByIdTime(@Param("gno") String gno, @Param("time") String time);
}