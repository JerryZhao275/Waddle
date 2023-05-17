package com.example.educationapplication.search.dataObjects;

import java.util.ArrayList;
import java.util.List;

public class QuizForumDiscussionDto extends QuizForumDto{
    private List<MessageDto> messages;
    private Integer quizId;

    public QuizForumDiscussionDto(Integer forumId, String forumName, Integer quizId) {
        super(forumId, forumName);
        this.quizId = quizId;
        messages = new ArrayList<MessageDto>();
    }

    public void createMessage(String userId, Integer courseId,String message){
        messages.add(new MessageDto(userId, courseId, quizId, message));
    }

}
