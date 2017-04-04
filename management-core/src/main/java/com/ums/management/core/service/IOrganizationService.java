//generator==
package com.ums.management.core.service;

import com.ums.management.core.model.Organization;

import java.util.List;

public interface IOrganizationService {
	Organization getOrganizationById(int id);
    List<Organization> getOrganizations();
    void deleteById(int id);
    void create(Organization organization);
    void update(Organization organization);
}