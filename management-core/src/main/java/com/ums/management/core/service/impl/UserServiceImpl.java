package com.ums.management.core.service.impl;

import com.ums.management.core.dao.*;
import com.ums.management.core.model.*;
import com.ums.management.core.service.IUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.DigestUtils;


import java.util.Collection;
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

    @Autowired
    private OrganizationMapper _orgDao = null;

    @Autowired
    private UserOrgMapper _userOrgDao = null;

    @Override
    public User getUserById(long id) {
        User u = _userDao.selectByPrimaryKey(id);
        u.setSalt(null);
        u.setPassword(null);
        return u;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = _userDao.selectAllUsers();
        for(User u : users) {
            u.setSalt(null);
            u.setPassword(null);
        }

        return users;
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        _urDao.deleteByPrimaryKey(id);
        _userOrgDao.deleteByUserId(id);
        _userDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void create(User user, Role role, List<Organization> orgs) {
        String salt = RandomStringUtils.randomNumeric(4);
        user.setSalt(salt);

        _userDao.insert(user);
        UserRole ur = new UserRole();
        ur.setUserId(user.getId());
        ur.setRoleId(role.getId());
        _urDao.insert(ur);

        //TODO: limit to my org list

        for(Organization org : orgs) {
            UserOrg uo = new UserOrg();
            uo.setOrgId(org.getId());
            uo.setUserId(user.getId());
            _userOrgDao.insert(uo);
        }
    }

    @Override
    @Transactional
    public void update(User user, Role role, List<Organization> orgs) {
        _userDao.updateByPrimaryKeySelective(user);
        UserRole ur = _urDao.selectByPrimaryKey(user.getId());
        if(ur.getRoleId() != role.getId()) {
            ur.setRoleId(role.getId());
            _urDao.updateByPrimaryKey(ur);
        }

        //TODO: limit to my org list
        //TODO:Optimize
        _userOrgDao.deleteByUserId(user.getId());
        for(Organization org : orgs) {
            UserOrg uo = new UserOrg();
            uo.setOrgId(org.getId());
            uo.setUserId(user.getId());
            _userOrgDao.insert(uo);
        }
    }

    @Override
    public Role getRoleByUser(User user) {
        UserRole userRole = _urDao.selectByPrimaryKey(user.getId());
        Role role = _roleDao.selectByPrimaryKey(userRole.getRoleId());
        return role;
    }

    @Override
    public List<Organization> getOrganizationsByUser(User user){
        return _orgDao.selectOrganizationsByUserId(user.getId());
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

    @Override
    public User login(String username, String password){
        User user = _userDao.selectByCode(username);
        if(user == null || user.getEnabled() == false)
            return null;
        String hash = DigestUtils.md5Hex(password + user.getSalt());
        if(!hash.equals(user.getPassword()))
            return null;
        user.setSalt(null);
        user.setPassword(null);
        return user;
    }
}
