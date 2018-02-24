package com.ums.management.core.service.impl;

import com.ums.management.core.dao.FileMetaMapper;
import com.ums.management.core.model.FileMeta;
import com.ums.management.core.service.IFileService;
import com.ums.management.core.service.IStorage;
import com.ums.management.core.utility.CommonException;
import com.ums.management.core.view.model.ServiceResult;
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
    public ServiceResult deleteFile(int id) {
        ServiceResult<Void> result = new ServiceResult(null);
        FileMeta meta = _metaDao.selectByPrimaryKey(id);
        _metaDao.deleteByPrimaryKey(id);
        try {
            _storage.delete(meta.getUri());
        } catch (CommonException ex) {
            result.setCode(404);
            result.setReason("File not found");
        }
        return result;
    }

    @Override
    public void replace(FileMeta meta) {
        _metaDao.updateByPrimaryKey(meta);
    }

    @Override
    public ServiceResult replace(FileMeta meta, InputStream stream) {
        ServiceResult<Void> result = new ServiceResult(null);
        try {
            _storage.delete(meta.getUri());
        } catch (CommonException ex) {
            result.setCode(404);
            result.setReason("File not found");
        }

        String uri = _storage.save(stream);
        meta.setUri(uri);
        _metaDao.updateByPrimaryKey(meta);

        return result;
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
