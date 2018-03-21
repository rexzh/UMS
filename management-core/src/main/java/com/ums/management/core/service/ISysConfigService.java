//generator==
package com.ums.management.core.service;

import java.util.List;

import com.ums.management.core.model.SysConfig;
import com.ums.management.core.view.model.ServiceResult;
import com.ums.management.core.view.model.UserVO;

public interface ISysConfigService {
	SysConfig getSysConfigById(int id);
    List<SysConfig> getSysConfigs();
    //void deleteById(int id);
    //void create(SysConfig sysConfig);
    ServiceResult<Void> update(UserVO editor, SysConfig sysConfig);
}