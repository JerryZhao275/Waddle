package com.example.educationapplication.search;

public class EmailExpression implements Exp{
    String email;
    Exp next;
    public EmailExpression(String email, Exp next){
        this.email = email;
        this.next = next;
    }
    @Override
    public String showExpType() {
        return "EMAIL";
    }

    @Override
    public String getCurrentValue() {
        return email;
    }

    @Override
    public Exp getNext() {
        return next;
    }
}
