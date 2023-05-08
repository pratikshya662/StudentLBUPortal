package com.pratikshya.StudentPortal.controller;

import com.pratikshya.StudentPortal.model.Course;
import com.pratikshya.StudentPortal.model.Enrollment;
import com.pratikshya.StudentPortal.service.CourseImpl;
import com.pratikshya.StudentPortal.service.EnrollmentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //-- next controlller use it
@CrossOrigin
@RequestMapping("/api")
public class EnrollmentController {
    @Autowired
    EnrollmentImpl enrollmentImpl;
    @GetMapping("/enrollments")
    public @ResponseBody List<Enrollment> enrollmentsPage(@RequestBody Authentication authentication){
        User userDetails = (User) authentication.getPrincipal();
        long studentId = Long.valueOf(userDetails.getUsername());
        return enrollmentImpl.getEnrollment(studentId);
    }
    @Autowired
    CourseImpl courseImpl;
    @GetMapping("/enrol/course/{courseid}")
    public @ResponseBody String  enrollmentsPage(Authentication authentication, @RequestBody String courseid){
        Enrollment enrollment= new Enrollment();
        enrollment.setCid(Long.valueOf(courseid));
        User userDetails = (User) authentication.getPrincipal();
        long studentId = Long.valueOf(userDetails.getUsername());
        enrollment.setSid(studentId);

        Course course = courseImpl.getCourseById(courseid);

        enrollmentImpl.saveEnrollment(enrollment,course.getCfee());
        return "Enrollment Successfull";
    }
}
