package dataObjects;

import java.util.List;

public class StudentUserDto extends UserDto {

    private Integer age;
    private String school;

    private List<String> courses;
    public StudentUserDto(){
        super();
        age = 0;
        school = "";
        courses = null;
    }
    public StudentUserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail, String userDesc, Integer age, String school) {
        super(userId, userFirstName, userLastName, userName, userEmail, userDesc);
        this.age = age;
        this.school = school;
    }

    public StudentUserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail, String userDesc, Integer age) {
        super(userId, userFirstName, userLastName, userName, userEmail, userDesc);
        this.age = age;
    }

    public StudentUserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail, Integer age) {
        super(userId, userFirstName, userLastName, userName, userEmail);
        this.age = age;
    }

    public StudentUserDto(UserDto user){
        super(user.getUserId(), user.getUserFirstName(), user.getUserLastName(), user.getUserName(), user.getUserEmail());
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
