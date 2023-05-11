package dataObjects;

import java.util.List;

public class CourseDto {
    //we can make this Integer or string as well. Up for more discussion.
    private Integer courseId;
    private String courseName;
    private String courseDescription;
    private List<QuizDto> quizzes;

    private TeacherUserDto teacher;
    private List<String> students;

    public CourseDto(Integer courseId, String courseName, TeacherUserDto teacher){
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacher = teacher;
    }
    public CourseDto(Integer courseId, String courseName, TeacherUserDto teacher, String courseDescription){
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacher = teacher;
        this.courseDescription = courseDescription;
    }
    public String getCourseDescription(){
        return this.courseDescription;
    }

    public void setCourseDescription(String courseDescription){
        this.courseDescription = courseDescription;
    }
    public String getTeacherId(){
        return teacher.getUserId();
    }

    public void enroll(String student){
        students.add(student);
    }

    public void addMultipleStudent(List<String> students){
        this.students.addAll(students);
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

    public Integer getCourseID(){
        return courseId;
    }

    public List<QuizDto> getQuizzes(){
        return quizzes;
    }

    public List<String> getAllStudents(){
        return students;
    }

}
