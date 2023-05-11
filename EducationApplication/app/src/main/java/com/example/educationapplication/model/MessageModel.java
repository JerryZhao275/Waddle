package com.example.educationapplication.model;

public class MessageModel {
    private final String sender;
    private final String message;

    public MessageModel(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }
}
