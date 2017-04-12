//generator==
package com.ums.management.core.service;

import java.util.List;

import com.ums.management.core.model.GlobalDict;

public interface IGlobalDictService {
	GlobalDict getGlobalDictById(int id);
    List<GlobalDict> getGlobalDicts();
    void deleteById(int id);
    void create(GlobalDict globalDict);
    void update(GlobalDict globalDict);
}