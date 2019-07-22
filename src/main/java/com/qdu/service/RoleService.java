package com.qdu.service;

import com.qdu.beans.CModule;
import com.qdu.beans.CRole;
import com.qdu.beans.CRoleModule;
import com.qdu.beans.CUserRole;

import java.util.List;
import java.util.Map;

/**
 * Created by Alpha-LGC on 2019/3/25.
 */
public interface RoleService {
    List<CRole> queryAllRoles();
    int addOrDelUsersRole(CUserRole ur,Integer flag);
    Map<String,Object> queryAllRolesPage(Integer page, Integer rows);
    int addRoleByName(CRole role);
    List<CModule> queryModulesByRoleId(CRole role);
    int addRolesModules(CRoleModule cm,Integer rows);
    int batchdeleteRolesById(List<Long> ids);
}
