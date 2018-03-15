package com.ums.management.web.controller;

import com.ums.management.core.model.UserExt;
import com.ums.management.core.service.IUserService;
import com.ums.management.core.view.model.ServiceResult;
import com.ums.management.web.utility.PageExtension;
import com.ums.management.web.utility.SessionExtension;
import com.ums.management.core.view.model.ChangePasswordVO;
import com.ums.management.web.view.vo.ResponseVO;
import com.ums.management.core.view.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


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
        UserVO currentUser = SessionExtension.getCurrentUser(httpSession);

        ResponseVO response = ResponseVO.buildSuccessResponse();
        Long start = PageExtension.calcStart(page, rows);

        response.addData("users", this._svc.getAllUsers(currentUser, code, name, enabled, start, rows));
        response.addData("count", this._svc.countAllUsers(currentUser, code, name, enabled));
        return response;
    }

    @RequestMapping("/user.json/{id}")
    public ResponseVO getUserById(@PathVariable("id") Integer userId) {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        UserVO user = this._svc.getUserById(userId);

        response.addData("user", user);
        return response;
    }

    @RequestMapping(value = "/user.json", method = RequestMethod.PUT)
    public ResponseVO updateUser(HttpSession httpSession, @RequestBody UserVO userVO) {
        UserVO editor = SessionExtension.getCurrentUser(httpSession);

        ServiceResult result = this._svc.update(editor, userVO);
        return ResponseVO.buildResponse(result);
    }

    @RequestMapping(value = "/user.json", method = RequestMethod.POST)
    public ResponseVO createUser(HttpSession httpSession, @RequestBody UserVO userVO) {
        UserVO editor = SessionExtension.getCurrentUser(httpSession);

        ServiceResult result = this._svc.create(editor, userVO);
        return ResponseVO.buildResponse(result);
    }

    @RequestMapping(value = "/user.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteUser(HttpSession httpSession, @PathVariable("id") long id) {
        UserVO editor = SessionExtension.getCurrentUser(httpSession);

        ServiceResult result = this._svc.deleteById(editor, id);
        return ResponseVO.buildResponse(result);
    }

    @RequestMapping(value = "/user.json/reset/{id}", method = RequestMethod.PUT)
    public ResponseVO resetUserPassword(HttpSession httpSession, @PathVariable("id") long id) {
        UserVO editor = SessionExtension.getCurrentUser(httpSession);

        ServiceResult<String> result = this._svc.resetPassword(editor, id);
        ResponseVO response = ResponseVO.buildResponse(result);
        if (result.getSuccess())
            response.addData("password", result.getResult());
        return response;
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

    @RequestMapping(value = "/profile.json", method = RequestMethod.GET)
    public ResponseVO getProfile(HttpSession session) {
        UserVO u = SessionExtension.getCurrentUser(session);
        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("profile", this._svc.getProfile(u.getId()));
        return response;
    }

    @RequestMapping(value = "/profile.json", method = RequestMethod.PUT)
    public ResponseVO updateProfile(HttpSession session, @RequestBody UserExt ext) {
        UserVO u = SessionExtension.getCurrentUser(session);
        ServiceResult result = this._svc.updateProfile(u.toUser(), ext);

        return ResponseVO.buildResponse(result);
    }
}
