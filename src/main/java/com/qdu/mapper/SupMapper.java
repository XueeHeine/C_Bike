package com.qdu.mapper;

import com.qdu.beans.Bicycle;
import com.qdu.beans.Sup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2019/4/16.
 */
@Mapper
@Repository
public interface SupMapper {


    int insert(Sup sup);

    int getCountBySname(@Param("sname") String sname);

    List<Sup> query(@Param("sname") String sname, @Param("priority") Integer priority);


    void remove(@Param("ids") List<Integer> ids);

    Sup getSupById(@Param("id") Integer id);

    int update(Sup sup);
}
