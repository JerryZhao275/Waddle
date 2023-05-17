package com.example.educationapplication.search.dataObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseDto implements Serializable {
    //we can make this Integer or string as well. Up for more discussion.
    private Integer courseId;
    private String courseName;
    private String courseDescription;
    private List<QuizDto> quizzes;

    private TeacherUserDto teacher;
    private List<String> students;
    public CourseDto(){
        courseId = 0;
        courseName = "";
        courseDescription = "";
        quizzes = new ArrayList<>();
        teacher = new TeacherUserDto();
        students = new ArrayList<>();
    }

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

    public Integer getCourseId(){
        return courseId;
    }

    public List<QuizDto> getQuizzes(){
        return quizzes;
    }

    public List<String> getAllStudents(){
        return students;
    }
    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public void setCourseId(Integer courseId){
        this.courseId = courseId;
    }
    public void setTeacher(TeacherUserDto teacher){
        this.teacher = teacher;
    }

    public void setQuizzes(List<QuizDto> quizzes){
        this.quizzes = quizzes;
    }

    public void addQuiz(QuizDto quiz){
        this.quizzes.add(quiz);
    }
    public void setStudents(List<String> students){
        this.students = students;
    }
    public TeacherUserDto getTeacher(){
        return teacher;
    }

    /*protected CourseDto(Parcel in) {
        courseId = in.readInt();
        courseName = in.readString();
        courseDescription = in.readString();
        students = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(courseId);
        dest.writeString(courseName);
        dest.writeString(courseDescription);
        dest.writeStringList(students);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CourseDto> CREATOR = new Creator<CourseDto>() {
        @Override
        public CourseDto createFromParcel(Parcel in) {
            return new CourseDto(in);
        }

        @Override
        public CourseDto[] newArray(int size) {
            return new CourseDto[size];
        }
    };*/
}
