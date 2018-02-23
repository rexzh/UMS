package com.ums.management.core.service.impl;

import com.ums.management.core.dao.FileMetaMapper;
import com.ums.management.core.model.FileMeta;
import com.ums.management.core.service.IFileService;
import com.ums.management.core.service.IStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Rex on 2017/4/28.
 */
@Service
public class FIleServiceImpl implements IFileService {

    @Autowired
    private FileMetaMapper _metaDao = null;

    @Autowired
    @Qualifier("SimpleStorage")
    private IStorage _storage = null;

    @Override
    public void putFile(FileMeta meta, InputStream stream) {
        String uri = _storage.save(stream);
        meta.setUri(uri);
        _metaDao.insert(meta);
    }

    @Override
    public FileMeta getFileMetaById(int id) {
        return _metaDao.selectByPrimaryKey(id);
    }

    @Override
    public InputStream getStream(FileMeta meta) {
        return _storage.read(meta.getUri());
    }

    @Override
    public void deleteFile(int id) {
        FileMeta meta = _metaDao.selectByPrimaryKey(id);
        _storage.delete(meta.getUri());
    }

    @Override
    public void replace(FileMeta meta) {
        _metaDao.updateByPrimaryKey(meta);
    }

    @Override
    public void replace(FileMeta meta, InputStream stream) {
        _storage.delete(meta.getUri());
        String uri = _storage.save(stream);
        meta.setUri(uri);
        _metaDao.updateByPrimaryKey(meta);
    }

    @Override
    public List<FileMeta> list(String name, String type, int start, int rows) {
        return _metaDao.selectAll(name, type, start, rows);
    }

    @Override
    public int count() {
        return _metaDao.countAll();
    }
}
