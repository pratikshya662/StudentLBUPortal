package com.pratikshya.StudentPortal.service;
import com.pratikshya.StudentPortal.Repo.CourseRepo;
import com.pratikshya.StudentPortal.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseImpl {
    @Autowired
    CourseRepo courseRepo;
    public List<Course> getCourse(){
        List<Course> courseList= courseRepo.findAll();
        return courseList;

    }
    public List<Course> searchCourse(String name) {
        List<Course> courseList = courseRepo.findByCName(name);
        return courseList;
    }
}
