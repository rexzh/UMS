package com.ums.management.web.controller;

import com.ums.management.core.model.Role;
import com.ums.management.core.model.RoleMenu;
import com.ums.management.core.model.User;
import com.ums.management.core.service.IUserService;
import com.ums.management.web.view.vo.ResponseVO;
import com.ums.management.web.view.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Rex on 2017/4/2.
 */
@RestController
public class UserController {
    @Autowired
    private IUserService _svc = null;

    @RequestMapping("/user.json")
    public ResponseVO getAllUsers(){
        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("users", this._svc.getUsers());
        return response;
    }


    @RequestMapping("/user.json/{id}")
    public ResponseVO getUserById(@PathVariable("id") Integer userId) {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        User user = this._svc.getUserById(userId);
        Role role = this._svc.getRoleByUser(user);

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setRole(role);

        response.addData("user", userVO);
        return response;
    }

    @RequestMapping(value = "/user.json", method = RequestMethod.PUT)
    public ResponseVO updateUser(@RequestBody UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        this._svc.update(user, userVO.getRole());

        ResponseVO response = ResponseVO.buildSuccessResponse();
        return response;
    }

    @RequestMapping(value = "/user.json", method = RequestMethod.POST)
    public ResponseVO createUser(@RequestBody UserVO userVO) {

        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        this._svc.create(user, userVO.getRole());

        ResponseVO response = ResponseVO.buildSuccessResponse();
        return response;
    }


    @RequestMapping(value = "/user.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteUser(@PathVariable("id") Integer id) {
        this._svc.deleteById(id);

        ResponseVO response = ResponseVO.buildSuccessResponse();
        return response;
    }
}
