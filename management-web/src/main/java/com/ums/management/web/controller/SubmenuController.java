package com.ums.management.web.controller;


import com.ums.management.core.model.Submenu;
import com.ums.management.core.service.IMenuService;
import com.ums.management.web.utility.SessionExtension;
import com.ums.management.web.view.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
public class SubmenuController {
    @Autowired
    private IMenuService _svc = null;

    @RequestMapping("/submenu.json")
    public ResponseVO getAllSubmenus() {
        ResponseVO response = ResponseVO.buildSuccessResponse();

        response.addData("submenus", _svc.getAllSubmenus());

        return response;
    }

    @RequestMapping("/submenu.json/{id}")
    public ResponseVO getSubmenuById(@PathVariable("id") Integer submenuId) {
        ResponseVO response = ResponseVO.buildSuccessResponse();

        response.addData("submenu", _svc.getSubmenuById(submenuId));

        return response;
    }

    @RequestMapping(value = "/submenu.json", method = RequestMethod.PUT)
    public ResponseVO updateSubmenu(HttpSession session, @RequestBody Submenu submenu) {

        ResponseVO response = ResponseVO.buildSuccessResponse();

        _svc.updateSubmenu(SessionExtension.getCurrentUser(session), submenu);

        return response;
    }

    @RequestMapping(value = "/submenu.json", method = RequestMethod.POST)
    public ResponseVO createSubmenu(HttpSession session, @RequestBody Submenu submenu) {

        ResponseVO response = ResponseVO.buildSuccessResponse();

        _svc.createSubmenu(SessionExtension.getCurrentUser(session), submenu);

        return response;
    }

    @RequestMapping(value = "/submenu.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteSubmenu(HttpSession session, @PathVariable("id") Integer id) {

        ResponseVO response = ResponseVO.buildSuccessResponse();

        _svc.deleteSubmenuById(SessionExtension.getCurrentUser(session), id);

        return response;
    }
}
