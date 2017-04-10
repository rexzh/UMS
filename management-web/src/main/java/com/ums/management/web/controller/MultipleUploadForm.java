package com.ums.management.web.controller;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;


public class MultipleUploadForm {
    private List<CommonsMultipartFile> files;

    public List<CommonsMultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<CommonsMultipartFile> files) {
        this.files = files;
    }
}
