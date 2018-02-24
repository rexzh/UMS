package com.ums.management.web.controller;

import com.ums.management.core.model.FileMeta;
import com.ums.management.core.service.IFileService;
import com.ums.management.core.utility.JSONExtension;
import com.ums.management.core.view.model.ServiceResult;
import com.ums.management.web.utility.PageExtension;
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
    public void replace(HttpServletResponse httpResponse, @PathVariable("id") int id, @RequestParam("file") MultipartFile file) throws IOException {
        FileMeta meta = _svc.getFileMetaById(id);
        meta.setName(file.getOriginalFilename());
        meta.setType(file.getContentType());

        try (InputStream is = file.getInputStream()) {
            ServiceResult result = _svc.replace(meta, is);

            ResponseVO response;

            if(result.getSuccess()) {
                response = ResponseVO.buildSuccessResponse();
                if(result.getCode() == 404) {
                    response.addData("warning", result.getReason());
                }
            } else {
                response = ResponseVO.buildErrorResponse(result.getReason());
            }

            response.addData("file", meta);

            String json = JSONExtension.stringify(response);

            httpResponse.setContentType("text/html");
            try (OutputStream os = httpResponse.getOutputStream()) {
                IOUtils.write(json, os);

                os.flush();
                os.close();
            }
        }
    }

    @RequestMapping(value = "/file.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO delete(@PathVariable("id") int id) {
        ServiceResult result = _svc.deleteFile(id);
        if(result.getSuccess()) {
            ResponseVO response = ResponseVO.buildSuccessResponse();

            if(result.getCode() == 404) {
                response.addData("warning", result.getReason());
            }
            return response;
        } else {
            return ResponseVO.buildErrorResponse(result.getReason());
        }
    }

    @RequestMapping(value = "/file.json", method = RequestMethod.GET)
    public ResponseVO list(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "type", required = false) String type,
                           @RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "rows", required = false) Integer rows) {
        if(page == null)
            page = 1;
        Integer start = PageExtension.calcStart(page, rows);
        ResponseVO responseVO = ResponseVO.buildSuccessResponse();
        responseVO.addData("files", _svc.list(name, type, start, rows));
        responseVO.addData("count", _svc.count());
        return responseVO;
    }
}
