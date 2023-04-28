package dataObjects;

public class QuizDto {
    private Integer quizId;
    private String quizTitle;
    private Integer courseId;

    private QuizForumQADto qaForum;

    private QuizForumDiscussionDto discussionForum;

    public QuizDto(Integer quizId, String quizTitle, Integer courseId){
        this.quizId = quizId;
        this.quizTitle = quizTitle;
        this.courseId = courseId;
    }

    public void createQAForum(Integer forumId, String forumName){
        this.qaForum = new QuizForumQADto(forumId, forumName, quizId);
    }

    public void createDiscussionForum(Integer forumId, String forumName){
        this.discussionForum = new QuizForumDiscussionDto(forumId, forumName, quizId);
    }

}
