//generator==
package com.ums.management.core.service;

import java.util.List;

import com.ums.management.core.model.LocalDict;

public interface ILocalDictService {
	LocalDict getLocalDictById(int id);
    List<LocalDict> getLocalDicts(Integer typeId, int orgId);
    List<LocalDict> getLocalDictByCodeAndOrgId(String code, int orgId);
    void deleteById(int id);
    void create(LocalDict localDict);
    void update(LocalDict localDict);
}