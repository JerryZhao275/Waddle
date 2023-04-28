package dataObjects;

import java.util.ArrayList;
import java.util.List;

public class MessageDto {
    private Integer userId;
    private String message;
    private Integer courseId;
    private Integer quizId;
    private List<MessageDto> replies;
    public MessageDto(Integer userId, Integer courseId, Integer quizId,String message){
        this.userId = userId;
        this.courseId = courseId;
        this.quizId = quizId;
        this.message = message;
        replies = new ArrayList<MessageDto>();
    }

    public void addReply(Integer userId, String message){
        this.replies.add(new MessageDto(userId, courseId, quizId, message));
    }
}
