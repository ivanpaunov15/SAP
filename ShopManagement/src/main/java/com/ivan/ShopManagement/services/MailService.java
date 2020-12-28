package com.ivan.ShopManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String receiver,String topic , String text) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("ivansaptask@gmail.com");
        mail.setTo(receiver);
        mail.setSubject(topic);
        mail.setText(text);

        javaMailSender.send(mail);
    }
}
