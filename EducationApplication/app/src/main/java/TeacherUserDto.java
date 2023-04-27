import java.util.List;

public class TeacherUserDto extends UserDto{
    private Integer age;
    private String school;
    private List<CourseDto> courses;
    public TeacherUserDto(Integer userId, String userFirstName, String userLastName, String userName, String userEmail, String userPassword, String userDesc, Integer age, String school) {
        super(userId, userFirstName, userLastName, userName, userEmail, userPassword, userDesc, "T");
        this.age = age;
        this.school = school;
    }

    public TeacherUserDto(Integer userId, String userFirstName, String userLastName, String userName, String userEmail, String userPassword, String userDesc, Integer age) {
        super(userId, userFirstName, userLastName, userName, userEmail, userPassword, userDesc, "T");
        this.age = age;
    }

    public TeacherUserDto(Integer userId, String userFirstName, String userLastName, String userName, String userEmail, String userPassword, Integer age) {
        super(userId, userFirstName, userLastName, userName, userEmail, userPassword, "T");
        this.age = age;
    }

    public void addCourse(CourseDto course){
        this.courses.add(course);
    }

    public void addCourses(List<CourseDto> courses){
        this.courses.addAll(courses);
    }


}
