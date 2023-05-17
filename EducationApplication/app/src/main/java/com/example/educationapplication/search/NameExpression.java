package com.example.educationapplication.search;

public class NameExpression implements Exp{
    String userName;
    Exp secondaryName;

    public NameExpression(String userName, Exp secondary){
        this.userName = userName;
        this.secondaryName = secondary;
    }
    @Override
    public String showExpType() {
        return "USER";
    }

    @Override
    public String getCurrentValue() {
        return userName;
    }

    @Override
    public Exp getNext() {
        return secondaryName;
    }
}
