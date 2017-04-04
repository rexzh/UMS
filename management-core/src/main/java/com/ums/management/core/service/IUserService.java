package com.ums.management.core.service;

import com.ums.management.core.model.Role;
import com.ums.management.core.model.User;

import java.util.List;

/**
 * Created by Rex on 2017/4/2.
 */
public interface IUserService {
    User getUserById(long id);
    List<User> getAllUsers();

    void deleteById(long id);
    void create(User user, Role role);
    void update(User user, Role role);

    Role getRoleByUser(User user);
}
