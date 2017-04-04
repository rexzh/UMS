package com.ums.management.web.controller;


import com.ums.management.core.model.Submenu;
import com.ums.management.core.service.IMenuService;
import com.ums.management.web.view.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Rex on 2016/9/4.
 */
@RestController
public class SubmenuController {
    @Autowired
    private IMenuService _svc = null;

    @RequestMapping("/submenu.json")
    public ResponseVO getAllSubmenus(){
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
    public ResponseVO updateSubmenu(@RequestBody Submenu submenu) {

        ResponseVO response = ResponseVO.buildSuccessResponse();

        _svc.updateSubmenu(submenu);

        return response;
    }

    @RequestMapping(value = "/submenu.json", method = RequestMethod.POST)
    public ResponseVO createSubmenu(@RequestBody Submenu submenu) {

        ResponseVO response = ResponseVO.buildSuccessResponse();

        _svc.createSubmenu(submenu);

        return response;
    }

    @RequestMapping(value = "/submenu.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteSubmenu(@PathVariable("id") Integer id) {

        ResponseVO response = ResponseVO.buildSuccessResponse();

        _svc.deleteSubmenuById(id);

        return response;
    }
}
