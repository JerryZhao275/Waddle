package com.example.educationapplication.search;

import java.util.List;

/**
 * Tokenizer that tokenizes queries in the search bar
 */
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

    /**
     * Tokenizes as User email if string contains @
     * and UserName otherwise when in user mode
     * Tokenizes as CourseName when the string is alphanumeric
     * and Course Description otherwise
     * @param mode
     */
    public void tokenize(char mode){
            if(index<allTokens.length) {
                if (mode == 'u') {
                    if (allTokens[index].contains("@")) {
                        currentToken = new Token(allTokens[index], Token.Type.EMAIL);
                    } else {
                        currentToken = new Token(allTokens[index], Token.Type.NAME);
                    }
                } else {
                    if (allTokens[index].matches(".*\\d.*") || allTokens[index].length()<=4) {
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

    /**
     * Checks if current token is null or not
     * @return boolean
     */
    public boolean hasNext(){
        return currentToken!=null;
    }

    /**
     * get current token
     * @return current token
     */
    public Token getCurrentToken(){
        return currentToken;
    }

}
