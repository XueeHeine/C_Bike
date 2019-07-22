package com.qdu.service;



import com.qdu.beans.Bicy;

import java.util.List;
import java.util.Map;

/**
 * Created by Alpha-LGC on 2018/11/16.
 */
public interface BicyService {
    void doAdd(Bicy bicy);

    Map<String,Object> query(Integer page, Integer rows, Bicy bicy);
    boolean update(Bicy bicy);
    Bicy getBicy(Bicy bicy);
    int remove(List<Integer> ids);
    List<Bicy> getAllXinghao();
    int subCount(String bno);
    int addCount(String bno);
    Bicy gettype(String bno);
}
