package com.example.educationapplication.search;

/**
 * Empty expression for parsing
 */
public class EmptyExpression implements Exp{
    public EmptyExpression(){

    }

    @Override
    public String showExpType() {
        return "EMPTY";
    }

    @Override
    public String getCurrentValue() {
        return null;
    }

    @Override
    public Exp getNext() {
        return null;
    }
}
