package com.ums.management.core.service;

import com.ums.management.core.model.Organization;
import com.ums.management.core.model.User;
import com.ums.management.core.view.model.ServiceResult;
import com.ums.management.core.view.model.UserVO;

import java.util.List;

public interface IOrganizationService {
	Organization getOrganizationById(int id);

    List<Organization> getOrganizations(UserVO editor, String name, Boolean enabled, Integer start, Integer rows);
    int countOrganizations(UserVO editor, String name, Boolean enabled);

    ServiceResult<Void> deleteById(UserVO editor, int id);

    ServiceResult<Void> create(UserVO editor, Organization organization);

    ServiceResult<Void> update(UserVO editor, Organization organization);
}