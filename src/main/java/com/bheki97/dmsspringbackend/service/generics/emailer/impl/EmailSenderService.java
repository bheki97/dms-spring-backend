package com.bheki97.dmsspringbackend.service.generics.emailer.impl;


import com.bheki97.dmsspringbackend.model.EmailMessage;
import com.bheki97.dmsspringbackend.service.generics.emailer.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service("emailServiceSender")
public class EmailSenderService implements EmailSender {


    private final JavaMailSender javaMailSender;

    public EmailSenderService(JavaMailSender javaMailSender) {

        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(EmailMessage message) {


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {

            helper= new MimeMessageHelper(mimeMessage, true);
            helper.setTo(message.getReceivers());
            helper.setSubject(message.getSubject());
            helper.setText(message.getMessage(), message.isHtml());
            List<File> attachments = message.getAttachments();

            if(attachments!=null && !attachments.isEmpty()){
                for(File f:attachments){
                    helper.addAttachment(f.getName(), f);
                }
            }

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {

            System.out.println(e.getMessage());
        }

    }
}
