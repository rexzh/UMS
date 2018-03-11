package com.ums.management.web.controller;

import com.ums.management.core.service.IUserService;
import com.ums.management.web.view.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;

import com.sun.management.OperatingSystemMXBean;

@RestController
public class AboutController {
    @Value("${app.env}")
    private String env = null;

    @Value("${app.version}")
    private String version = null;

    @RequestMapping(value = "/system.json")
    public ResponseVO system() {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("env", env);
        response.addData("version", version);

        int cpu = Runtime.getRuntime().availableProcessors();

        OperatingSystemMXBean mx = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        DecimalFormat fmt = new DecimalFormat("#.0");
        String memory = fmt.format((float) mx.getTotalPhysicalMemorySize() / 1024 / 1024 / 1024);

        response.addData("memory", memory);
        response.addData("cpu", cpu);

        return response;
    }

    @RequestMapping(value = "/about.json")
    public ResponseVO about() {
        ResponseVO response = ResponseVO.buildSuccessResponse();

        response.addData("powered by", "quartzx");

        return response;
    }
}
