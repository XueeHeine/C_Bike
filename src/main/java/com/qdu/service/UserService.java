package com.qdu.service;


import com.qdu.beans.CUser;

import java.util.List;
import java.util.Map;

/**
 * Created by Alpha-LGC on 2019/2/12.
 */
public interface UserService {
    Map<String,Object> queryAllUser(Integer page, Integer rows);
    int insert(CUser user);
    int update(CUser user);
    int deleteByPrimaryKeys(List<Long> ids);
}
