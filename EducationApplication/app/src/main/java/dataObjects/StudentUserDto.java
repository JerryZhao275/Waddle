package dataObjects;

import java.util.List;

public class StudentUserDto extends UserDto {

    private Integer age;
    private String school;

    private List<CourseDto> courses;
    public StudentUserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail, String userPassword, String userDesc, Integer age, String school) {
        super(userId, userFirstName, userLastName, userName, userEmail, userPassword, userDesc, "S");
        this.age = age;
        this.school = school;
    }

    public StudentUserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail, String userPassword, String userDesc, Integer age) {
        super(userId, userFirstName, userLastName, userName, userEmail, userPassword, userDesc,"S");
        this.age = age;
    }

    public StudentUserDto(String userId, String userFirstName, String userLastName, String userName, String userEmail, String userPassword, Integer age) {
        super(userId, userFirstName, userLastName, userName, userEmail, userPassword, "S");
        this.age = age;
    }

    public void addCourse(CourseDto course){
        courses.add(course);
    }

    public void addCourses(List<CourseDto> courses){
        this.courses.addAll(courses);
    }

    public List<CourseDto> getAllEnrolledCourses(){
        return courses;
    }


}
