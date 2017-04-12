//generate==
package com.ums.management.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ums.management.core.service.IGlobalDictService;
import com.ums.management.core.model.GlobalDict;
import com.ums.management.web.view.vo.ResponseVO;

@RestController
public class GlobalDictController {
	@Autowired
	private IGlobalDictService _svc = null;

	@RequestMapping("/globalDict.json")
	public ResponseVO getGlobalDicts(@RequestParam(value = "typeId", required = false) Integer typeId) {
		ResponseVO response = ResponseVO.buildSuccessResponse();
		response.addData("globalDicts", _svc.getGlobalDicts(typeId));
        return response;
	}

	@RequestMapping("/globalDict.json/{id}")
    public ResponseVO getGlobalDictById(@PathVariable("id") Integer globalDictId) {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("globalDict", _svc.getGlobalDictById(globalDictId));
        return response;
    }

	@RequestMapping(value = "/globalDict.json", method = RequestMethod.PUT)
    public ResponseVO updateGlobalDict(@RequestBody GlobalDict globalDict) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
		_svc.update(globalDict);
        return response;
    }

    @RequestMapping(value = "/globalDict.json", method = RequestMethod.POST)
    public ResponseVO createGlobalDict(@RequestBody GlobalDict globalDict) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
		_svc.create(globalDict);
        return response;
    }

    @RequestMapping(value = "/globalDict.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteGlobalDict(@PathVariable("id") Integer id) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
		_svc.deleteById(id);
        return response;
    }
}