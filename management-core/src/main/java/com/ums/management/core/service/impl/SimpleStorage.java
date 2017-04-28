package com.ums.management.core.service.impl;

import com.ums.management.core.service.IStorage;
import com.ums.management.core.utility.CommonException;
import com.ums.management.core.utility.UUIDExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Rex on 2017/4/28.
 */
@Service("SimpleStorage")
public class SimpleStorage implements IStorage {
    @Value("${upload.path}")
    private String rootPath = null;

    @Override
    public String save(InputStream stream) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        String dir = fmt.format(new Date());

        try {
            Path p = Paths.get(rootPath, dir);
            if (Files.notExists(p))
                Files.createDirectory(p);
            String fileName = UUIDExtension.uuidToBase64(UUID.randomUUID());
            Path file = p.resolve(fileName);


            Files.copy(stream, file);
            String uri = Paths.get(rootPath).relativize(file).toString();

            return uri;
        } catch (IOException ioe) {
            throw new CommonException("Storage error", ioe);
        }
    }

    @Override
    public InputStream read(String uri) {
        try {
            Path file = Paths.get(rootPath, uri);
            return Files.newInputStream(file);
        } catch (IOException ioe) {
            throw new CommonException("Storage error", ioe);
        }
    }

    @Override
    public void delete(String uri) {
        try {
            Path file = Paths.get(rootPath, uri);
            Files.delete(file);
        } catch (IOException ioe) {
            throw new CommonException("Storage error", ioe);
        }
    }
}
