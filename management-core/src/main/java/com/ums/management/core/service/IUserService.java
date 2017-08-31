package com.ums.management.core.service;

import com.ums.management.core.model.Organization;
import com.ums.management.core.model.Role;
import com.ums.management.core.model.User;
import com.ums.management.core.view.model.ChangePasswordVO;
import com.ums.management.core.view.model.LoginVO;
import com.ums.management.core.view.model.ServiceResult;
import com.ums.management.core.view.model.UserVO;

import javax.xml.ws.Service;
import java.util.List;

public interface IUserService {
    UserVO getUserById(long id);

    List<UserVO> getAllUsers(UserVO editor, String code, String name, Boolean enabled, Long start, Integer rows);
    long countAllUsers(UserVO editor, String code, String name, Boolean enabled);

    ServiceResult<Void> deleteById(UserVO editor, long id);
    ServiceResult<Void> create(UserVO editor, UserVO user);
    ServiceResult<Void> update(UserVO editor, UserVO user);

    Role getRoleByUser(User user);
    List<Organization> getOrganizationsByUser(User user);

    ServiceResult<String> resetPassword(UserVO editor, long id);
    UserVO login(LoginVO login);
    boolean changePassword(ChangePasswordVO changePassword);
}
