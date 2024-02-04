package com.bheki97.dmsspringbackend.service.generics.emailer;


import com.bheki97.dmsspringbackend.model.EmailMessage;

public interface EmailSender {

    void sendEmail(EmailMessage message);
}
