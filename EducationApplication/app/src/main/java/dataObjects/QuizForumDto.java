package dataObjects;

import java.util.List;

public abstract class QuizForumDto {
    private Integer forumId;
    private String forumName;
    public QuizForumDto(Integer forumId, String forumName){
        this.forumId = forumId;
        this.forumName = forumName;
    }
}
