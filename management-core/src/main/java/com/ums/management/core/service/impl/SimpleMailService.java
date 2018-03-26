package com.ums.management.core.service.impl;

import com.ums.management.core.service.IMailService;
import com.ums.management.core.service.impl.Mail.MailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SimpleMailService implements IMailService {
    @Value("${mail.smtp.host}")
    private String smtpHost = null;

    @Value("${mail.smtp.port}")
    private Integer smtpPort = null;

    @Value("${mail.smtp.ssl}")
    private Boolean ssl = null;

    @Value("${mail.smtp.auth}")
    private Boolean auth = null;

    @Value("${mail.smtp.username}")
    private String username = null;

    @Value("${mail.smtp.password}")
    private String password = null;

    private MailSender _sender;

    @PostConstruct
    public void Initialize() {
        _sender = new MailSender(smtpHost, smtpPort, ssl, auth);
        if(auth) {
            _sender.setUsername(this.username);
            _sender.setPassword(this.password);
        }
    }

    @Override
    public void sendMail(String sendTo, String subject, String content) {
        _sender.send(sendTo, subject, content);
    }
}
