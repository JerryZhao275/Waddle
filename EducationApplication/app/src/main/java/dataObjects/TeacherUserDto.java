package dataObjects;

import java.util.List;

public class TeacherUserDto extends UserDto {
    private Integer age;
    private String school;
    private List<String> courses = null;
    public TeacherUserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail, String userPassword, String userDesc, Integer age, String school) {
        super(userId, userFirstName, userLastName, userName, userEmail, userPassword, userDesc, "T");
        this.age = age;
        this.school = school;
    }

    public TeacherUserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail, String userPassword, String userDesc, Integer age) {
        super(userId, userFirstName, userLastName, userName, userEmail, userPassword, userDesc, "T");
        this.age = age;
    }

    public TeacherUserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail, String userPassword, Integer age) {
        super(userId, userFirstName, userLastName, userName, userEmail, userPassword, "T");
        this.age = age;
    }

    public TeacherUserDto(UserDto user){
        super(user.getUserId(), user.getUserFirstName(), user.getUserLastName(), user.getUserName(), user.getUserEmail(), user.getUserPassword(), "T");

    }

    public void setSchool(String school){
        this.school = school;
    }

    public String getSchool(){
        return this.school;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public Integer getAge(){
        return this.age;
    }

    public void addCourse(String course){
        this.courses.add(course);
    }

    public void addCourses(List<String> courses){
        this.courses.addAll(courses);
    }

    public List<String> getCourses(){
        return courses;
    }

}
