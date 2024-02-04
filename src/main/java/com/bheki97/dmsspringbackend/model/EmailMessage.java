package com.bheki97.dmsspringbackend.model;

import lombok.Data;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Data
public class EmailMessage {

    private String[] receivers;
    private String subject;
    private String message;
    private boolean isHtml;
    private List<File> attachments;

    public EmailMessage() {

    }

    public EmailMessage(String[] receivers, String subject, String message, boolean isHtml, List<File> attachments) {
        this.receivers = receivers;
        this.subject = subject;
        this.message = message;
        this.isHtml = isHtml;
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "EmailMessage{" +
                "receivers=" + Arrays.toString(receivers) +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", isHtml=" + isHtml +
                ", attachments=" + Arrays.toString(attachments.toArray()) +
                '}';
    }
}
