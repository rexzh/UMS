package com.ums.management.core.service.impl.Mail;

import com.ums.management.core.utility.CommonException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class MailSender {
    private String host;
    private Integer port;
    private Boolean ssl = false;
    private Boolean authenticate = false;
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MailSender(String host, Integer port, Boolean ssl, Boolean authenticate) {
        this.host = host;
        this.port = port;
        this.ssl = ssl;
        this.authenticate = authenticate;
    }

    public void send(String sendTo, String subject, String content) {
        Properties props = System.getProperties();
        Session session = null;

        props.setProperty("mail.smtp.host", this.host);
        props.put("mail.smtp.port", this.port);

        if(this.ssl) {
            props.put("mail.smtp.socketFactory.port", this.port);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }

        MailAuthenticator authenticator = new MailAuthenticator(this.username, this.password);
        if (this.authenticate) {
            props.setProperty("mail.smtp.auth", "true");
            session = Session.getInstance(props, authenticator);
        } else {
            session = Session.getInstance(props);
        }

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(authenticator.getUsername(), authenticator.getUsername()));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(sendTo, sendTo));
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setContent(content, "text/html;charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new CommonException("Send mail fail.", e);
        } catch (UnsupportedEncodingException e) {
            throw new CommonException("Send mail fail.", e);
        }
    }
}
