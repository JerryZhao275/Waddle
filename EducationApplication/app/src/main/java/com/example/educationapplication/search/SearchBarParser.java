package com.example.educationapplication.search;

/**
 * Parser for the search bar queries
 */
public class SearchBarParser {
    SearchBarTokenizer tokenizer;
    public SearchBarParser(SearchBarTokenizer tokenizer){
        this.tokenizer = tokenizer;
    }

    /**
     * Returns expression for user search
     * Grammer: user: <user, email>|email|empty
     * @return Expression
     */
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

    /**
     * Returns expression for user search
     * Grammer: email: <email, email>|user|empty
     * @return Expression
     */
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
    /**
     * Returns expression for course search
     * Grammer: course name: <course name, course description>|course description|empty
     * @return Expression
     */
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
    /**
     * Returns expression for course search
     * Grammer: course description: <course description, course description>|course name|empty
     * @return Expression
     */
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
