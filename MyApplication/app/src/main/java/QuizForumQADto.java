import java.util.ArrayList;
import java.util.List;

public class QuizForumQADto extends QuizForumDto{
    private List<MessageDto> messages;
    private Integer quizId;

    public QuizForumQADto(Integer forumId, String forumName,Integer quizId) {
        super(forumId, forumName);
        this.quizId = quizId;
        messages = new ArrayList<MessageDto>();
    }


}
