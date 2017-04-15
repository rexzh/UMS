package com.ums.management.web.controller;

import com.ums.management.core.model.Menu;
import com.ums.management.web.view.vo.ResponseVO;
import com.ums.management.core.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @RequestMapping("/menu.json/{id}")
    public ResponseVO getMenuById(@PathVariable("id") Integer menuId) {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("menu", _svc.getMenuById(menuId));
        return response;
    }

    @RequestMapping(value = "/menu.json", method = RequestMethod.PUT)
    public ResponseVO updateMenu(@RequestBody Menu menu) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
        _svc.updateMenu(menu);
        return response;
    }

    @RequestMapping(value = "/menu.json", method = RequestMethod.POST)
    public ResponseVO createMenu(@RequestBody Menu menu) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
        _svc.createMenu(menu);
        return response;
    }

    @RequestMapping(value = "/menu.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteMenu(@PathVariable("id") Integer id) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
        _svc.deleteMenuById(id);
        return response;
    }
}
