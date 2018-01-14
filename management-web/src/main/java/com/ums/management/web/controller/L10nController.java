//generate==
package com.ums.management.web.controller;


import com.ums.management.core.utility.JSONExtension;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ums.management.core.service.IL10nService;
import com.ums.management.core.model.L10n;
import com.ums.management.web.view.vo.ResponseVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@RestController
public class L10nController {
	@Autowired
	private IL10nService _svc = null;

	@RequestMapping("/l10n/{code}")
    public ResponseVO buildL10NJavaScript(HttpServletResponse httpResponse, @PathVariable("code") String code) throws IOException {
        Map<String, String> result = _svc.getPackageByCode(code);

        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("data", result);
        return response;
    }

	@RequestMapping("/l10n.json")
	public ResponseVO getL10ns() {
		ResponseVO response = ResponseVO.buildSuccessResponse();
		response.addData("l10ns", _svc.getL10ns());
        return response;
	}

	@RequestMapping("/l10n.json/{code}")
    public ResponseVO getL10nByCode(@PathVariable("code") String code) {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("l10n", _svc.getL10nByCode(code));
        return response;
    }

	@RequestMapping(value = "/l10n.json", method = RequestMethod.PUT)
    public ResponseVO updateL10n(@RequestBody L10n l10n) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
		_svc.update(l10n);
        return response;
    }

    @RequestMapping(value = "/l10n.json", method = RequestMethod.POST)
    public ResponseVO createL10n(@RequestBody L10n l10n) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
		_svc.create(l10n);
        return response;
    }

    @RequestMapping(value = "/l10n.json/{code}", method = RequestMethod.DELETE)
    public ResponseVO deleteL10n(@PathVariable("code") String code) {
        ResponseVO response = ResponseVO.buildSuccessResponse();
		_svc.deleteL10nByCode(code);
        return response;
    }
}