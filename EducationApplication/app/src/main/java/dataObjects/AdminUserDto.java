package dataObjects;

import java.util.List;

public class AdminUserDto extends UserDto {
    private Integer age;
    private String school;
    private List<String> courses = null;

    public AdminUserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail, String userPassword, String userDesc) {
        super(userId, userFirstName, userLastName, userName, userEmail, userPassword, userDesc, null);
    }
    public AdminUserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail, String userPassword) {
        super(userId, userFirstName, userLastName, userName, userEmail, userPassword, null);
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
        courses.add(course);
    }

    public void addCourses(List<String> courses){
        this.courses.addAll(courses);
    }

    public List<String> getCourses(){
        return courses;
    }

}
