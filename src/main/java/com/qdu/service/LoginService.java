package com.qdu.service;

import com.qdu.beans.CModule;
import com.qdu.beans.CRole;
import com.qdu.beans.CUser;

import java.util.List;

/**
 * Created by Alpha-LGC on 2019/1/25.
 */
public interface LoginService {
    CUser  getUserByUS(CUser user);
    List<CRole> queryRolesByUserId(CUser user);
    List<CModule> queryModulesByRoleId(CRole role);
    CUser  getUserByName(CUser user);
}
