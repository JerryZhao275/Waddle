package com.example.educationapplication.search;

/**
 * Class for token for tokenizer and parser
 */
public class Token {
    public enum Type {NAME, EMAIL,COURSENAME, COURSEDESC}
    private String token;
    private Type tokenType;
    public Token(String token, Type tokenType){
        this.token = token;
        this.tokenType = tokenType;
    }
    public String getToken(){
        return token;
    }
    public Type getTokenType(){
        return tokenType;
    }
}
