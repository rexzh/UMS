package com.ums.management.core.service;

import com.ums.management.core.model.FileMeta;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Rex on 2017/4/28.
 */
public interface IFileService {
    void putFile(FileMeta meta, InputStream stream);
    FileMeta getFileMetaById(int id);
    InputStream getStream(FileMeta meta);
    void deleteFile(int id);
    void replace(FileMeta meta);
    void replace(FileMeta meta, InputStream stream);

    List<FileMeta> list(String name, String type, int start, int rows);
    int count();
}
