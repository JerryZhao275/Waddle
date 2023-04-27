package dataObjects;

import java.util.List;

public class CourseDto {
    //we can make this Integer or string as well. Up for more discussion.
    private Integer courseId;
    private String courseName;
    private List<QuizDto> quizzes;

    private TeacherUserDto teacher;
    private List<StudentUserDto> students;

    public CourseDto(Integer courseId, String courseName, TeacherUserDto teacher){
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacher = teacher;
    }

    public Integer getTeacherId(){
        return teacher.getUserId();
    }

    public void enroll(StudentUserDto student){
        students.add(student);
    }

    public void addMultipleStudent(List<StudentUserDto> students){
        students.addAll(students);
    }

    public void createQuiz(Integer quizId, String quizTitle){
        this.quizzes.add(new QuizDto(quizId, quizTitle, courseId));
    }

    public void createQuizzes(List<QuizDto> quizzes){
        this.quizzes.addAll(quizzes);
    }

    public String getCourseName(){
        return courseName;
    }

    public List<QuizDto> getQuizzes(){
        return quizzes;
    }

    public List<StudentUserDto> getAllStudents(){
        return students;
    }

}
