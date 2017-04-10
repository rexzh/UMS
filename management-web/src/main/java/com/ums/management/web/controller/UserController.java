package com.ums.management.web.controller;

import com.ums.management.core.model.Organization;
import com.ums.management.core.model.Role;
import com.ums.management.core.model.User;
import com.ums.management.core.service.IUserService;
import com.ums.management.web.utility.PageExtension;
import com.ums.management.web.utility.UserExtension;
import com.ums.management.web.view.vo.ChangePasswordVO;
import com.ums.management.web.view.vo.ResponseVO;
import com.ums.management.web.view.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    private IUserService _svc = null;

    @RequestMapping("/user.json")
    public ResponseVO getAllUsers(@RequestParam(value = "code", required = false) String code,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "enabled", required = false) Boolean enabled,
                                  @RequestParam(value = "page", required = false) Long page,
                                  @RequestParam(value = "rows", required = false) Integer rows){
        ResponseVO response = ResponseVO.buildSuccessResponse();
        Long start = PageExtension.calcStart(page, rows);
        response.addData("users", this._svc.getAllUsers(code, name, enabled, start, rows));
        response.addData("count", this._svc.countAllUsers(code, name, enabled));
        return response;
    }

    @RequestMapping("/user.json/{id}")
    public ResponseVO getUserById(@PathVariable("id") Integer userId) {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        User user = this._svc.getUserById(userId);
        Role role = this._svc.getRoleByUser(user);
        List<Organization> orgs = this._svc.getOrganizationsByUser(user);

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setRole(role);
        userVO.setOrganizations(orgs);

        response.addData("user", userVO);
        return response;
    }

    @RequestMapping(value = "/user.json", method = RequestMethod.PUT)
    public ResponseVO updateUser(HttpSession httpSession, @RequestBody UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);

        Role oldRole = _svc.getRoleByUser(user);
        if(UserExtension.hasEnoughPower(httpSession, oldRole) && UserExtension.hasEnoughPower(httpSession, userVO.getRole())) {
            this._svc.update(user, userVO.getRole(), userVO.getOrganizations());
            return ResponseVO.buildSuccessResponse();
        } else {
            return ResponseVO.buildErrorResponse("Can't edit Admin user");
        }
    }

    @RequestMapping(value = "/user.json", method = RequestMethod.POST)
    public ResponseVO createUser(HttpSession httpSession, @RequestBody UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);

        UserVO curUser = UserExtension.getCurrentUser(httpSession);
        if(UserExtension.hasEnoughPower(httpSession, userVO.getRole())){
            this._svc.create(user, userVO.getRole(), userVO.getOrganizations());
            return ResponseVO.buildSuccessResponse();
        } else {
            return ResponseVO.buildErrorResponse("Can't create Admin user");
        }
    }

    @RequestMapping(value = "/user.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteUser(HttpSession httpSession, @PathVariable("id") long id) {
        User user = _svc.getUserById(id);
        Role role = _svc.getRoleByUser(user);

        if(UserExtension.hasEnoughPower(httpSession, role)) {
            this._svc.deleteById(id);
            return ResponseVO.buildSuccessResponse();
        } else {
            return ResponseVO.buildErrorResponse("Can't delete Admin user");
        }
    }

    @RequestMapping(value = "/user.json/reset/{id}", method = RequestMethod.PUT)
    public ResponseVO resetUserPassword(HttpSession httpSession, @PathVariable("id") long id) {
        User user = _svc.getUserById(id);
        Role role = _svc.getRoleByUser(user);

        if(UserExtension.hasEnoughPower(httpSession, role)) {
            String password = this._svc.resetPassword(id);
            ResponseVO response = ResponseVO.buildSuccessResponse();
            response.addData("password", password);
            return response;
        } else {
            return ResponseVO.buildErrorResponse("Can't reset password for Admin user");
        }
    }

    @RequestMapping(value = "/user.json/chgpwd", method = RequestMethod.PUT)
    public ResponseVO changeUserPassword(@RequestBody ChangePasswordVO chgpwd) {
        boolean result = _svc.changePassword(chgpwd.getId(), chgpwd.getOldPassword(), chgpwd.getNewPassword());
        if(result) {
            return ResponseVO.buildSuccessResponse();
        } else {
            return ResponseVO.buildErrorResponse("Password not correct");
        }
    }
}
