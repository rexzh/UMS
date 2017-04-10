package com.ums.management.web.controller;

import com.ums.management.web.view.vo.ResponseVO;
import com.ums.management.core.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MenuController {
    @Autowired
    private IMenuService _svc;

    @RequestMapping("/menu.json")
    public ResponseVO getAllMenus(){
        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("menus", this._svc.getAllMenus());
        return response;
    }
}
