//generate==
package com.ums.management.web.controller;

import com.ums.management.core.model.DictType;
import com.ums.management.web.view.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ums.management.core.service.IDictTypeService;

@RestController
public class DictTypeController {
	@Autowired
	private IDictTypeService _svc = null;

	@RequestMapping("/dictType.json")
	public ResponseVO getDictTypes(@RequestParam(value = "code", required = false) String code,
                                   @RequestParam(value = "global", required = false) Boolean global) {
		ResponseVO response = ResponseVO.buildSuccessResponse();
		response.addData("dictTypes", _svc.getDictTypes(code, global));
        return response;
	}

	@RequestMapping("/dictType.json/{id}")
    public ResponseVO getDictTypeById(@PathVariable("id") Integer dictTypeId) {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("dictType", _svc.getDictTypeById(dictTypeId));
        return response;
    }

	@RequestMapping(value = "/dictType.json", method = RequestMethod.PUT)
    public ResponseVO updateDictType(@RequestBody DictType dictType) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
		_svc.update(dictType);
        return response;
    }

    @RequestMapping(value = "/dictType.json", method = RequestMethod.POST)
    public ResponseVO createDictType(@RequestBody DictType dictType) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
		_svc.create(dictType);
        return response;
    }

    @RequestMapping(value = "/dictType.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteDictType(@PathVariable("id") Integer id) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
		_svc.deleteById(id);
        return response;
    }
}