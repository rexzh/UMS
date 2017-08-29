package com.ums.management.core.service.impl;

import com.ums.management.core.dao.*;
import com.ums.management.core.model.*;
import com.ums.management.core.service.IUserService;
import com.ums.management.core.utility.CopyUtils;
import com.ums.management.core.view.model.ChangePasswordVO;
import com.ums.management.core.view.model.LoginVO;
import com.ums.management.core.view.model.UserVO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.DigestUtils;


import java.util.*;

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
    public List<UserVO> getAllUsers(String code, String name, Boolean enabled, Long start, Integer rows) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("code", code);
        queryMap.put("name", name);
        queryMap.put("enabled", enabled);

        queryMap.put("start", start);
        queryMap.put("rows", rows);

        List<User> users = _userDao.selectAllUsers(queryMap);

        List<UserVO> result = CopyUtils.copyBeanList(users, UserVO.class);
        for (UserVO u : result) {
            u.setSalt(null);
            u.setPassword(null);

            UserRole ur = _urDao.selectByPrimaryKey(u.getId());
            Role role = _roleDao.selectByPrimaryKey(ur.getRoleId());
            u.setRole(role);
        }

        return result;
    }

    @Override
    public List<UserVO> getAllUsersByUserId(long userId, String code, String name, Boolean enabled, Long start, Integer rows) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("code", code);
        queryMap.put("name", name);
        queryMap.put("enabled", enabled);
        queryMap.put("userId", userId);

        queryMap.put("start", start);
        queryMap.put("rows", rows);

        List<User> users = _userDao.selectAllUsersByUserId(queryMap);

        List<UserVO> result = CopyUtils.copyBeanList(users, UserVO.class);
        for (UserVO u : result) {
            u.setSalt(null);
            u.setPassword(null);

            UserRole ur = _urDao.selectByPrimaryKey(u.getId());
            Role role = _roleDao.selectByPrimaryKey(ur.getRoleId());
            u.setRole(role);
        }

        return result;
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
    public void create(UserVO user) {
        String salt = RandomStringUtils.randomNumeric(4);
        user.setSalt(salt);

        _userDao.insert(CopyUtils.copyBean(user, User.class));
        UserRole ur = new UserRole();
        ur.setUserId(user.getId());
        ur.setRoleId(user.getRole().getId());
        _urDao.insert(ur);

        for (Organization org : user.getOrganizations()) {
            UserOrg uo = new UserOrg();
            uo.setOrgId(org.getId());
            uo.setUserId(user.getId());
            _userOrgDao.insert(uo);
        }
    }

    @Override
    @Transactional
    public void update(UserVO user) {
        _userDao.updateByPrimaryKeySelective(CopyUtils.copyBean(user, User.class));
        UserRole ur = _urDao.selectByPrimaryKey(user.getId());
        if (ur.getRoleId() != user.getRole().getId()) {
            ur.setRoleId(user.getRole().getId());
            _urDao.updateByPrimaryKey(ur);
        }

        _userOrgDao.deleteByUserId(user.getId());
        for (Organization org : user.getOrganizations()) {
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
    public List<Organization> getOrganizationsByUser(User user) {
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
    public UserVO login(LoginVO login) {
        User user = _userDao.selectByCode(login.getUsername());
        if (user == null || user.getEnabled() == false)
            return null;
        String hash = DigestUtils.md5Hex(login.getPassword() + user.getSalt());
        if (!hash.equals(user.getPassword()))
            return null;
        user.setSalt(null);
        user.setPassword(null);

        UserVO userVO = CopyUtils.copyBean(user, UserVO.class);

        Role role = this.getRoleByUser(user);
        userVO.setRole(role);
        List<Organization> orgs = this.getOrganizationsByUser(user);
        List<Organization> filteredOrgs = new ArrayList<>();
        for (Organization org : orgs) {
            if (org.getEnabled())
                filteredOrgs.add(org);
        }
        userVO.setOrganizations(filteredOrgs);
        if (filteredOrgs.size() > 0)
            userVO.setCurrentOrganization(filteredOrgs.get(0));


        return userVO;
    }

    @Override
    public boolean changePassword(ChangePasswordVO changePassword) {
        User user = _userDao.selectByPrimaryKey(changePassword.getId());
        if (user.getPassword().equals(DigestUtils.md5Hex(changePassword.getOldPassword() + user.getSalt()))) {
            String digest = DigestUtils.md5Hex(changePassword.getNewPassword() + user.getSalt());
            user.setPassword(digest);
            _userDao.updateByPrimaryKey(user);
            return true;
        } else {
            return false;
        }
    }
}
