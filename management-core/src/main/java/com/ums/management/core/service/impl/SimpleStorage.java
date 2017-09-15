package com.ums.management.core.service.impl;

import com.ums.management.core.service.IStorage;
import com.ums.management.core.utility.CommonException;
import com.ums.management.core.utility.UUIDExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
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

            if (!Files.exists(p)){
                String os = System.getProperty("os.name");
                if(os.toLowerCase().indexOf("win") >= 0) {
                    Files.createDirectories(p);
                } else {
                    Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");
                    FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);

                    Files.createDirectories(p, attr);
                }
                logger.info("folder created");
            } else {
                logger.info("folder exist");
            }

            String fileName = UUIDExtension.uuidToBase64(UUID.randomUUID());
            Path file = p.resolve(fileName);
            Files.createFile(file);


            Files.copy(stream, file, StandardCopyOption.REPLACE_EXISTING);

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
