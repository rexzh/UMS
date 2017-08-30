package com.ums.management.web.controller;

import com.ums.management.core.model.Organization;
import com.ums.management.core.model.Role;
import com.ums.management.core.model.User;
import com.ums.management.core.service.IUserService;
import com.ums.management.core.utility.CopyUtils;
import com.ums.management.core.utility.ListExtension;
import com.ums.management.core.utility.RoleMatrix;
import com.ums.management.web.utility.PageExtension;
import com.ums.management.web.utility.UserExtension;
import com.ums.management.core.view.model.ChangePasswordVO;
import com.ums.management.web.view.vo.ResponseVO;
import com.ums.management.core.view.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    private IUserService _svc = null;

    @RequestMapping("/user.json")
    public ResponseVO getAllUsers(HttpSession httpSession,
                                  @RequestParam(value = "code", required = false) String code,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "enabled", required = false) Boolean enabled,
                                  @RequestParam(value = "page", required = false) Long page,
                                  @RequestParam(value = "rows", required = false) Integer rows) {
        UserVO currentUser = UserExtension.getCurrentUser(httpSession);

        ResponseVO response = ResponseVO.buildSuccessResponse();
        Long start = PageExtension.calcStart(page, rows);

        response.addData("users", this._svc.getAllUsers(currentUser, code, name, enabled, start, rows));
        response.addData("count", this._svc.countAllUsers(currentUser, code, name, enabled));
        return response;
    }

    @RequestMapping("/user.json/{id}")
    public ResponseVO getUserById(@PathVariable("id") Integer userId) {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        User user = this._svc.getUserById(userId);
        Role role = this._svc.getRoleByUser(user);
        List<Organization> orgs = this._svc.getOrganizationsByUser(user);

        UserVO userVO = CopyUtils.copyBean(user, UserVO.class);
        userVO.setRole(role);
        userVO.setOrganizations(orgs);

        response.addData("user", userVO);
        return response;
    }

    @RequestMapping(value = "/user.json", method = RequestMethod.PUT)
    public ResponseVO updateUser(HttpSession httpSession, @RequestBody UserVO userVO) {
        User user = CopyUtils.copyBean(userVO, User.class);
        Role oldRole = _svc.getRoleByUser(user);

        if (RoleMatrix.hasEnoughPower(UserExtension.getCurrentUser(httpSession).getRole(), oldRole) && RoleMatrix.hasEnoughPower(UserExtension.getCurrentUser(httpSession).getRole(), userVO.getRole())) {
            UserVO editor = UserExtension.getCurrentUser(httpSession);

            if (ListExtension.inclusion(editor.getOrganizations(), userVO.getOrganizations(), Comparator.comparing(Organization::getId))) {
                this._svc.update(userVO);
                return ResponseVO.buildSuccessResponse();
            } else {
                return ResponseVO.buildErrorResponse("No Permission");
            }
        } else {
            return ResponseVO.buildErrorResponse("No Permission");
        }
    }

    @RequestMapping(value = "/user.json", method = RequestMethod.POST)
    public ResponseVO createUser(HttpSession httpSession, @RequestBody UserVO userVO) {
        if (RoleMatrix.hasEnoughPower(UserExtension.getCurrentUser(httpSession).getRole(), userVO.getRole())) {
            UserVO editor = UserExtension.getCurrentUser(httpSession);
            if (ListExtension.inclusion(editor.getOrganizations(), userVO.getOrganizations(), Comparator.comparing(Organization::getId))) {
                this._svc.create(userVO);
                return ResponseVO.buildSuccessResponse();
            } else {
                return ResponseVO.buildErrorResponse("No Permission");
            }
        } else {
            return ResponseVO.buildErrorResponse("No Permission");
        }
    }

    @RequestMapping(value = "/user.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteUser(HttpSession httpSession, @PathVariable("id") long id) {
        User user = _svc.getUserById(id);
        Role role = _svc.getRoleByUser(user);

        if (RoleMatrix.hasEnoughPower(UserExtension.getCurrentUser(httpSession).getRole(), role)) {
            if (UserExtension.getCurrentUser(httpSession).getId() != id) {
                this._svc.deleteById(id);
                return ResponseVO.buildSuccessResponse();
            } else {
                return ResponseVO.buildErrorResponse("Can't delete current user");
            }
        } else {
            return ResponseVO.buildErrorResponse("No Permission");
        }
    }

    @RequestMapping(value = "/user.json/reset/{id}", method = RequestMethod.PUT)
    public ResponseVO resetUserPassword(HttpSession httpSession, @PathVariable("id") long id) {
        User user = _svc.getUserById(id);
        Role role = _svc.getRoleByUser(user);

        if (RoleMatrix.hasEnoughPower(UserExtension.getCurrentUser(httpSession).getRole(), role)) {
            String password = this._svc.resetPassword(id);
            ResponseVO response = ResponseVO.buildSuccessResponse();
            response.addData("password", password);
            return response;
        } else {
            return ResponseVO.buildErrorResponse("No Permission");
        }
    }

    @RequestMapping(value = "/user.json/chgpwd", method = RequestMethod.PUT)
    public ResponseVO changeUserPassword(@RequestBody ChangePasswordVO chgpwd) {
        boolean result = _svc.changePassword(chgpwd);
        if (result) {
            return ResponseVO.buildSuccessResponse();
        } else {
            return ResponseVO.buildErrorResponse("Password not correct");
        }
    }
}
