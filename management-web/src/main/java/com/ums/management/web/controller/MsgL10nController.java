//generate==
package com.ums.management.web.controller;

import com.ums.management.core.view.model.MsgL10nVO;
import com.ums.management.web.utility.PageExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ums.management.core.service.IL10nService;
import com.ums.management.core.model.MsgL10n;
import com.ums.management.web.view.vo.ResponseVO;

import java.util.*;

@RestController
public class MsgL10nController {
    @Autowired
    private IL10nService _svc = null;

    @RequestMapping("/msgL10n.json")
    public ResponseVO getMsgL10ns(@RequestParam(value = "msg", required = false) String msg,
                                  @RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "rows", required = false) Integer rows) {
        Integer start = PageExtension.calcStart(page, rows);
        ResponseVO response = ResponseVO.buildSuccessResponse();
        List<MsgL10n> msgs = _svc.getMsgL10n(msg, start, rows);
        List<MsgL10nVO> result = new ArrayList<>();
        for(MsgL10n msgL10n : msgs) {
            MsgL10nVO vo = new MsgL10nVO();
            vo.setMsg(msgL10n.getMsg());

            List<MsgL10n> translations = _svc.getMsgL10nByMsg(msgL10n.getMsg());
            for(MsgL10n translation : translations) {
                vo.getL10n().put(translation.getCode(), translation.getTransMsg());
            }

            result.add(vo);
        }

        response.addData("msgL10ns", result);
        response.addData("count", _svc.countMsgL10n(msg));
        return response;
    }


    @RequestMapping("/msgL10n.json/{msg}")
    public ResponseVO getMsgL10nById(@PathVariable("msg") String msg) {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("msgL10n", _svc.getMsgL10nByMsg(msg));
        return response;
    }


    @RequestMapping(value = "/msgL10n.json", method = RequestMethod.POST)
    public ResponseVO createMsgL10n(@RequestBody List<MsgL10n> msgL10ns) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
        _svc.saveMsgL10n(msgL10ns);
        return response;
    }

    @RequestMapping(value = "/msgL10n.json/{msg}", method = RequestMethod.DELETE)
    public ResponseVO deleteMsgL10n(@PathVariable("msg") String msg) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
        _svc.deleteMsgL10nByMsg(msg);
        return response;
    }
}