package com.ums.management.core.service;

public interface IMailService {
    void sendMail(String sendTo, String subject, String content);
}
