//generate==
package com.ums.management.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ums.management.core.service.ISysConfigService;
import com.ums.management.core.model.SysConfig;
import com.ums.management.web.view.vo.ResponseVO;

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

	/*
	@RequestMapping("/sysConfig.json/{id}")
    public ResponseVO getSysConfigById(@PathVariable("id") Integer sysConfigId) {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("sysConfig", _svc.getSysConfigById(sysConfigId));
        return response;
    }
    */

	@RequestMapping(value = "/sysConfig.json", method = RequestMethod.PUT)
    public ResponseVO updateSysConfig(@RequestBody SysConfig sysConfig) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
		_svc.update(sysConfig);
        return response;
    }

    /*
    @RequestMapping(value = "/sysConfig.json", method = RequestMethod.POST)
    public ResponseVO createSysConfig(@RequestBody SysConfig sysConfig) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
		_svc.create(sysConfig);
        return response;
    }

    @RequestMapping(value = "/sysConfig.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteSysConfig(@PathVariable("id") Integer id) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
		_svc.deleteById(id);
        return response;
    }
    */
}