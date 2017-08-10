//generator==
package com.ums.management.core.service;

import java.util.List;

import com.ums.management.core.model.SysConfig;

public interface ISysConfigService {
	SysConfig getSysConfigById(int id);
    List<SysConfig> getSysConfigs();
    void deleteById(int id);
    void create(SysConfig sysConfig);
    void update(SysConfig sysConfig);
}