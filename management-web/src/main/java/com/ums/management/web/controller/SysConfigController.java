//generate==
package com.ums.management.web.controller;

import com.ums.management.core.view.model.ServiceResult;
import com.ums.management.web.utility.SessionExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ums.management.core.service.ISysConfigService;
import com.ums.management.core.model.SysConfig;
import com.ums.management.web.view.vo.ResponseVO;

import javax.servlet.http.HttpSession;

@RestController
public class SysConfigController {
	@Autowired
	private ISysConfigService _svc = null;

	@RequestMapping("/sysConfig.json")
	public ResponseVO getSysConfigs() {
		ResponseVO response = ResponseVO.buildSuccessResponse();
		response.addData("sysConfigs", _svc.getSysConfigs());
        return response;
	}

	@RequestMapping(value = "/sysConfig.json", method = RequestMethod.PUT)
    public ResponseVO updateSysConfig(HttpSession session, @RequestBody SysConfig sysConfig) {
		ServiceResult result = _svc.update(SessionExtension.getCurrentUser(session), sysConfig);
        return ResponseVO.buildResponse(result);
    }
}