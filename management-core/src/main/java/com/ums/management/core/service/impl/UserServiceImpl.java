package com.ums.management.core.service.impl;

import com.ums.management.core.dao.RoleMapper;
import com.ums.management.core.dao.UserMapper;
import com.ums.management.core.dao.UserRoleMapper;
import com.ums.management.core.model.Role;
import com.ums.management.core.model.User;
import com.ums.management.core.model.UserRole;
import com.ums.management.core.service.IUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

/**
 * Created by Rex on 2017/4/2.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper _userDao = null;

    @Autowired
    private UserRoleMapper _urDao = null;

    @Autowired
    private RoleMapper _roleDao = null;

    @Override
    public User getUserById(long id) {
        return _userDao.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getAllUsers() {
        return _userDao.selectAllUsers();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        _urDao.deleteByPrimaryKey(id);
        _userDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void create(User user, Role role) {
        String salt = RandomStringUtils.randomNumeric(4);
        user.setSalt(salt);

        _userDao.insert(user);
        UserRole ur = new UserRole();
        ur.setUserId(user.getId());
        ur.setRoleId(role.getId());
        _urDao.insert(ur);
    }

    @Override
    @Transactional
    public void update(User user, Role role) {
        _userDao.updateByPrimaryKey(user);
        UserRole ur = _urDao.selectByPrimaryKey(user.getId());
        if(ur.getRoleId() != role.getId()) {
            ur.setRoleId(role.getId());
            _urDao.updateByPrimaryKey(ur);
        }
    }

    @Override
    public Role getRoleByUser(User user) {
        UserRole userRole = _urDao.selectByPrimaryKey(user.getId());
        Role role = _roleDao.selectByPrimaryKey(userRole.getRoleId());
        return role;
    }

    @Override
    public String resetPassword(long id) {
        User user = _userDao.selectByPrimaryKey(id);
        String password = RandomStringUtils.randomNumeric(6);
        String hash = DigestUtils.md5Hex(password + user.getSalt());
        user.setPassword(hash);
        _userDao.updateByPrimaryKey(user);
        return password;
    }
}
