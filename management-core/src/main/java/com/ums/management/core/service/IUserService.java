package com.ums.management.core.service;

import com.ums.management.core.model.Organization;
import com.ums.management.core.model.Role;
import com.ums.management.core.model.User;
import com.ums.management.core.view.model.ChangePasswordVO;
import com.ums.management.core.view.model.LoginVO;
import com.ums.management.core.view.model.UserVO;

import java.util.List;

public interface IUserService {
    User getUserById(long id);

    List<UserVO> getAllUsers(String code, String name, Boolean enabled, Long start, Integer rows);
    long countAllUsers(String code, String name, Boolean enabled);

    List<UserVO> getAllUsersByUserId(long userId, String code, String name, Boolean enabled, Long start, Integer rows);
    long countAllUsersByUserId(long userId, String code, String name, Boolean enabled);

    void deleteById(long id);
    void create(UserVO user);
    void update(UserVO user);

    Role getRoleByUser(User user);
    List<Organization> getOrganizationsByUser(User user);

    String resetPassword(long id);
    UserVO login(LoginVO login);
    boolean changePassword(ChangePasswordVO changePassword);
}
