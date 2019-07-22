package com.qdu.service;

import com.qdu.beans.Sup;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2019/4/16.
 */
public interface SupService {
    void doAdd(Sup sup);

    Map<String,Object> query(Integer page, Integer rows, String sname, Integer priority);

    int doRemove(List<Integer> ids);

    Sup getSupById(Integer id);

    void doUpdate(Sup bicycle);
}
