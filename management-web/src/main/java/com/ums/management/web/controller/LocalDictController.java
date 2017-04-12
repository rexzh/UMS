//generate==
package com.ums.management.web.controller;

import com.ums.management.core.model.Role;
import com.ums.management.web.utility.UserExtension;
import com.ums.management.web.view.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ums.management.core.service.ILocalDictService;
import com.ums.management.core.model.LocalDict;
import com.ums.management.web.view.vo.ResponseVO;

import javax.servlet.http.HttpSession;

@RestController
public class LocalDictController {
	@Autowired
	private ILocalDictService _svc = null;

	@RequestMapping("/localDict.json")
	public ResponseVO getLocalDicts(HttpSession httpSession,
	                                @RequestParam(value = "typeId", required = false) Integer typeId,
                                    @RequestParam(value = "orgId", required = false) Integer orgId) {
		ResponseVO response = ResponseVO.buildSuccessResponse();
		response.addData("localDicts", _svc.getLocalDicts(typeId, orgId));
        return response;
	}

	@RequestMapping("/localDict.json/{id}")
    public ResponseVO getLocalDictById(@PathVariable("id") Integer localDictId) {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("localDict", _svc.getLocalDictById(localDictId));
        return response;
    }

	@RequestMapping(value = "/localDict.json", method = RequestMethod.PUT)
    public ResponseVO updateLocalDict(@RequestBody LocalDict localDict) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
		_svc.update(localDict);
        return response;
    }

    @RequestMapping(value = "/localDict.json", method = RequestMethod.POST)
    public ResponseVO createLocalDict(@RequestBody LocalDict localDict) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
		_svc.create(localDict);
        return response;
    }

    @RequestMapping(value = "/localDict.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteLocalDict(@PathVariable("id") Integer id) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
		_svc.deleteById(id);
        return response;
    }
}