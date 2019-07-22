package com.qdu.service.impl;

import com.qdu.beans.CModule;
import com.qdu.mapper.CModuleMapper;
import com.qdu.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alpha-LGC on 2019/4/11.
 */
@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private CModuleMapper cm;

    @Override
    public List<CModule> queryAllModules() {
        return cm.queryAllModules();
    }
}
