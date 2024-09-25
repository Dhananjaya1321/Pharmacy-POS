package com.novalabsglobal.pharmacy.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {
    @Autowired
    private JavaMailSender mailSender;


    public void sendSimpleEmail(String toEmail, String subject, String body) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom("myprojectisuru@gmail.com");
            helper.setTo(toEmail); // Recipient's email
            helper.setSubject(subject); // Email subject
            helper.setText(body, true); // Set the email body (true indicates HTML)

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
