package com.ums.management.core.service;

import com.ums.management.core.model.FileMeta;

import java.io.InputStream;

/**
 * Created by Rex on 2017/4/18.
 */
public interface IStorage {
    String save(InputStream stream);
    InputStream read(String uri);
    void delete(String uri);
}
