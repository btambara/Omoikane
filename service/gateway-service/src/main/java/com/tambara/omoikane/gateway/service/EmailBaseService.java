package com.tambara.omoikane.gateway.service;

public interface EmailBaseService {
    void sendEmail(String to, String subject, String text);

    void sendEmailWithAttachment(String to, String subject, String text, String pathToAttachment);
}
