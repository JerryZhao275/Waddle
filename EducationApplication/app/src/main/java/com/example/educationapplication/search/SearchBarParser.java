package com.example.educationapplication.search;

public class SearchBarParser {
    SearchBarTokenizer tokenizer;
    public SearchBarParser(SearchBarTokenizer tokenizer){
        this.tokenizer = tokenizer;
    }

    public Exp parseName(){
        if(tokenizer.hasNext()){
            Token current = tokenizer.getCurrentToken();

            if(current.getTokenType().equals(Token.Type.NAME)){
                tokenizer.tokenize('u');
                return new NameExpression(current.getToken(), parseEmail());
            }
            return parseEmail();
        }
        return new EmptyExpression();
    }

    public Exp parseEmail(){
        if(tokenizer.hasNext()){
            Token current = tokenizer.getCurrentToken();

            if(current.getTokenType().equals(Token.Type.EMAIL)){
                tokenizer.tokenize('u');
                return new EmailExpression(current.getToken(), parseEmail());
            }
            return parseName();
        }
        return new EmptyExpression();
    }

    public Exp parseCourse(){
        if(tokenizer.hasNext()){
            Token current = tokenizer.getCurrentToken();

            if(current.getTokenType().equals(Token.Type.COURSENAME)){
                tokenizer.tokenize('c');
                return new CourseExpression(current.getToken(), parseCourseDesc());
            }
            return parseCourseDesc();
        }
        return new EmptyExpression();
    }

    public Exp parseCourseDesc(){
        if(tokenizer.hasNext()){
            Token current = tokenizer.getCurrentToken();

            if(current.getTokenType().equals(Token.Type.COURSEDESC)){
                tokenizer.tokenize('c');
                return new CourseDescExpression(current.getToken(), parseCourseDesc());
            }
            return parseCourse();
        }
        return new EmptyExpression();
    }
}
