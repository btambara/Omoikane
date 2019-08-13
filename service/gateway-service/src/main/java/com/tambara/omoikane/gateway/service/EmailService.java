package com.tambara.omoikane.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailService implements EmailBaseService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String subject, String text) {
        sendEmailWithAttachment(to, subject, text, null);
    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String text, String pathToAttachment) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, pathToAttachment != null, "utf-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            if (pathToAttachment != null) {
                FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
                helper.addAttachment("File", file);
            }

            javaMailSender.send(message);
        } catch (MessagingException exception) {
            exception.printStackTrace();
        }
    }
}
