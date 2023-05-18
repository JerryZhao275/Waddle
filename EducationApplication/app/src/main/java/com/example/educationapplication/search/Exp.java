package com.example.educationapplication.search;

/**
 * Interface for parsing expressions
 */
public interface Exp {
    public String showExpType();
    public String getCurrentValue();
    public Exp getNext();
}
