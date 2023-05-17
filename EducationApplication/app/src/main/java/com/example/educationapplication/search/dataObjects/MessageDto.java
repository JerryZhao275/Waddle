package com.example.educationapplication.search.dataObjects;

import java.util.ArrayList;
import java.util.List;

public class MessageDto {
    private String userId;
    private String message;
    private Integer courseId;
    private Integer quizId;
    private List<MessageDto> replies;
    public MessageDto(String userId, Integer courseId, Integer quizId,String message){
        this.userId = userId;
        this.courseId = courseId;
        this.quizId = quizId;
        this.message = message;
        replies = new ArrayList<>();
    }

    public void addReply(String userId, String message){
        this.replies.add(new MessageDto(userId, courseId, quizId, message));
    }

    public String getMessage(){return message;}
}
