package com.ums.management.core.service;

import com.ums.management.core.model.Organization;
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
    void create(User user, Role role, List<Organization> orgs);
    void update(User user, Role role, List<Organization> orgs);

    Role getRoleByUser(User user);
    List<Organization> getOrganizationsByUser(User user);

    String resetPassword(long id);
}
