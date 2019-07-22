package com.qdu.mapper;


import com.qdu.beans.Bicy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Alpha-LGC on 2018/11/16.
 */
@Mapper
@Repository
public interface BicyMapper {
    int add(Bicy bicy);
    List<Bicy> getAllXinghao();
    int update(Bicy bicy);
    List<Bicy> query(Bicy bicy);
    Bicy getBicy(Bicy bicy);
    int remove(@Param("ids") List<Integer> ids);//批量删除
    int subCount(@Param("bno") String bno);
    int addCount(@Param("bno") String bno);
    Bicy gettype(@Param("bno") String bno);
}
