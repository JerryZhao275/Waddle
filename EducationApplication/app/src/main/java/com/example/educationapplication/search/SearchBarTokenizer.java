package com.example.educationapplication.search;

import java.util.List;

public class SearchBarTokenizer {
    private String[] allTokens;
    private Token currentToken;
    private int index = 0;
    public SearchBarTokenizer(String searchInput, char mode){
            allTokens = searchInput.split("[ ,]+");
            if(allTokens.length>0) {
                tokenize(mode);
            }
    }

    public void tokenize(char mode){
            if(index<allTokens.length) {
                if (mode == 'u') {
                    if (allTokens[index].contains("@")) {
                        currentToken = new Token(allTokens[index], Token.Type.EMAIL);
                    } else {
                        currentToken = new Token(allTokens[index], Token.Type.NAME);
                    }
                } else {
                    if (allTokens[index].matches(".*\\d.*")) {
                        currentToken = new Token(allTokens[index], Token.Type.COURSENAME);
                    } else {
                        currentToken = new Token(allTokens[index], Token.Type.COURSEDESC);
                    }
                }
                index++;
            }
            else{
                currentToken = null;
            }
    }

    public boolean hasNext(){
        return currentToken!=null;
    }

    public Token getCurrentToken(){
        return currentToken;
    }

}
