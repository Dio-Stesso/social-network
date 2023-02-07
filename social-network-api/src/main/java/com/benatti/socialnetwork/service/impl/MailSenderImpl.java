package com.benatti.socialnetwork.service.impl;

import com.benatti.socialnetwork.service.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderImpl implements MailSender {
    private final JavaMailSender mailSender;

    public MailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public boolean send(String emailTo, String subject, String messageText) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailTo);
            message.setSubject(subject);
            message.setText(messageText);
            mailSender.send(message);
            return true;
    }
}
