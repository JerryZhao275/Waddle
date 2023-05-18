package com.example.educationapplication.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.educationapplication.BR;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClient;
import com.example.educationapplication.integration.database.WaddleDatabaseServiceClientFactory;
import com.example.educationapplication.integration.database.config.ConfigurationManager;
import com.example.educationapplication.integration.database.config.WaddleDatabaseConfiguration;

import com.example.educationapplication.search.dataObjects.CourseDto;
import com.example.educationapplication.search.dataObjects.CustomOnCompleteListener;
import com.example.educationapplication.search.dataObjects.TeacherUserDto;

public class CreateClassViewModel extends BaseObservable {
    private CourseDto course;
    private String courseName;
    private String courseDescription;
    private TeacherUserDto teacher;
    private final WaddleDatabaseConfiguration config;
    private final WaddleDatabaseServiceClient databaseServiceClient;

    public CreateClassViewModel() {
        config = ConfigurationManager.configInstance();
        databaseServiceClient = WaddleDatabaseServiceClientFactory.createClient(config);
        course = new CourseDto();
    }

    /**
     * Set the teacher for the course.
     *
     * @param teacher The teacher for the course.
     */
    public void setTeacher(TeacherUserDto teacher) {
        this.teacher = teacher;
    }

    /**
     * Get the course name.
     *
     * @return The course name.
     */
    @Bindable
    public String getCourseName() {
        return courseName;
    }

    /**
     * Set the course name.
     *
     * @param courseName The course name to be set.
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
        course.setCourseName(courseName);
        notifyPropertyChanged(BR.courseName);
    }

    /**
     * Get the course description.
     *
     * @return The course description.
     */
    @Bindable
    public String getCourseDescription() {
        return courseDescription;
    }

    /**
     * Set the course description.
     *
     * @param courseDescription The course description to be set.
     */
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
        course.setCourseDescription(courseDescription);
        System.out.println(this.courseDescription);
        notifyPropertyChanged(BR.courseDescription);
    }

    /**
     * Create a new course.
     *
     * @param listener The listener to be notified when the course creation is complete.
     */
    public void createCourse(CustomOnCompleteListener listener) {
        String first = courseName.substring(0, 4);
        String second = courseName.substring(4);
        if (!first.matches("[0-9]+") && second.matches("[0-9]+") && second.length() == 4 && first.length() == 4) {
            System.out.println("Yo");
            course.setCourseId(Integer.parseInt(second));
            System.out.println(teacher);
            course.setTeacher(teacher);
            databaseServiceClient.addCourse(course, new CustomOnCompleteListener() {
                @Override
                public void onComplete() {
                    listener.onComplete();
                }
            });
        }
    }
}