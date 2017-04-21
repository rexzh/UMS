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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<User> getAllUsers(String code, String name, Boolean enabled, Long start, Integer rows) {
        Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("code", code);
		queryMap.put("name", name);
		queryMap.put("enabled", enabled);

		queryMap.put("start", start);
		queryMap.put("rows", rows);

        List<User> users = _userDao.selectAllUsers(queryMap);
        for(User u : users) {
            u.setSalt(null);
            u.setPassword(null);
        }

        return users;
    }

    @Override
    public List<User> getAllUsersByUserId(long userId, String code, String name, Boolean enabled, Long start, Integer rows) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("code", code);
        queryMap.put("name", name);
        queryMap.put("enabled", enabled);
        queryMap.put("userId", userId);

        queryMap.put("start", start);
        queryMap.put("rows", rows);

        List<User> users = _userDao.selectAllUsersByUserId(queryMap);
        for(User u : users) {
            u.setSalt(null);
            u.setPassword(null);
        }

        return users;
    }

    @Override
    public long countAllUsers(String code, String name, Boolean enabled) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("code", code);
        queryMap.put("name", name);
        queryMap.put("enabled", enabled);

        return _userDao.countAllUsers(queryMap);
    }

    @Override
    public long countAllUsersByUserId(long userId, String code, String name, Boolean enabled) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("code", code);
        queryMap.put("name", name);
        queryMap.put("enabled", enabled);
        queryMap.put("userId", userId);

        return _userDao.countAllUsersByUserId(queryMap);
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
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("userId", user.getId());

        return _orgDao.selectOrganizationsByUserId(queryMap);
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

    @Override
    public boolean changePassword(long id, String oldPassword, String newPassword){
        User user = _userDao.selectByPrimaryKey(id);
        if(user.getPassword().equals(DigestUtils.md5Hex(oldPassword + user.getSalt()))) {
            String digest = DigestUtils.md5Hex(newPassword + user.getSalt());
            user.setPassword(digest);
            _userDao.updateByPrimaryKey(user);
            return true;
        } else {
            return false;
        }
    }
}
