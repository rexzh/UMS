//generator==
package com.ums.management.core.service;

import com.ums.management.core.model.DictType;

import java.util.List;

public interface IDictTypeService {
	DictType getDictTypeById(int id);
    List<DictType> getDictTypes();
    void deleteById(int id);
    void create(DictType dictType);
    void update(DictType dictType);
}