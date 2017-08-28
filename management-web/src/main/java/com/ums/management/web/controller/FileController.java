package com.ums.management.web.controller;

import com.ums.management.core.model.FileMeta;
import com.ums.management.core.service.IFileService;
import com.ums.management.core.utility.JSONExtension;
import com.ums.management.web.view.vo.ResponseVO;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Rex on 2017/4/17.
 */
@RestController
public class FileController {
    @Autowired
    private IFileService _svc = null;

    @RequestMapping(value = "/file.json", method = RequestMethod.POST)
    public void upload(HttpServletResponse httpResponse, @RequestParam("file") MultipartFile file) throws IOException {
        FileMeta meta = new FileMeta();
        meta.setName(file.getOriginalFilename());
        meta.setType(file.getContentType());
        try (InputStream is = file.getInputStream()) {
            _svc.putFile(meta, is);
        }

        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("file", meta);

        String json = JSONExtension.stringify(response);

        httpResponse.setContentType("text/html");
        try(OutputStream os = httpResponse.getOutputStream()) {
            IOUtils.write(json, os);

            os.flush();
            os.close();
        }
    }

    @RequestMapping(value = "/file.json/{id}", method = RequestMethod.GET)
    public void download(HttpServletResponse response, @PathVariable("id") int id) throws IOException {
        FileMeta meta = _svc.getFileMetaById(id);
        try (InputStream is = _svc.getStream(meta);
             OutputStream os = response.getOutputStream()) {

            response.addHeader("Content-disposition", String.format("attachment;filename=%s", meta.getName()));
            response.setContentType(meta.getType());

            IOUtils.copy(is, os);

            os.flush();
            os.close();
        }
    }

    @RequestMapping(value = "/file.json/{id}", method = RequestMethod.POST)
    public ResponseVO replace(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) throws IOException {
        FileMeta meta = _svc.getFileMetaById(id);
        meta.setName(file.getOriginalFilename());
        meta.setType(file.getContentType());

        try (InputStream is = file.getInputStream()) {
            _svc.replace(meta, is);
        }

        return ResponseVO.buildSuccessResponse();
    }

    @RequestMapping(value = "/file.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO delete(@PathVariable("id") int id) {
        _svc.deleteFile(id);

        return ResponseVO.buildSuccessResponse();
    }
}
