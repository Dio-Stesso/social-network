package com.benatti.socialnetwork.service;

public interface MailSender {
    boolean send(String emailTo, String subject, String message);
}
