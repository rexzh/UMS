package com.ums.management.core.service;

import com.ums.management.core.model.Organization;
import com.ums.management.core.model.User;

import java.util.List;

public interface IOrganizationService {
	Organization getOrganizationById(int id);
    List<Organization> getOrganizations(String name, Boolean enabled, Integer start, Integer rows);
    int countOrganizations(String name, Boolean enabled);
    void deleteById(int id);
    void create(Organization organization);
    void update(Organization organization);
}