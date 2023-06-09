package com.pratikshya.StudentPortal.controller;

import com.pratikshya.StudentPortal.model.Course;
import com.pratikshya.StudentPortal.service.CourseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //-- next controlller use it
@CrossOrigin
@RequestMapping("/api")
public class CourseController {
    @Autowired
    CourseImpl courseImpl;

    @GetMapping("/courses")
    public List<Course> coursesPage(){

        return courseImpl.getCourse();
    }
}
