package com.ums.management.web.controller;

import com.ums.management.core.model.Menu;
import com.ums.management.web.utility.SessionExtension;
import com.ums.management.web.view.vo.ResponseVO;
import com.ums.management.core.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
public class MenuController {
    @Autowired
    private IMenuService _svc;

    @RequestMapping("/menu.json")
    public ResponseVO getAllMenus() {
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
    public ResponseVO updateMenu(HttpSession session, @RequestBody Menu menu) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
        _svc.updateMenu(SessionExtension.getCurrentUser(session), menu);
        return response;
    }

    @RequestMapping(value = "/menu.json", method = RequestMethod.POST)
    public ResponseVO createMenu(HttpSession session, @RequestBody Menu menu) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
        _svc.createMenu(SessionExtension.getCurrentUser(session), menu);
        return response;
    }

    @RequestMapping(value = "/menu.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteMenu(HttpSession session, @PathVariable("id") Integer id) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
        _svc.deleteMenuById(SessionExtension.getCurrentUser(session), id);
        return response;
    }
}
