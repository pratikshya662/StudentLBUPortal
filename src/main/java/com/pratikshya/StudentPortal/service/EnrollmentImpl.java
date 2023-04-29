package com.pratikshya.StudentPortal.service;
import com.pratikshya.StudentPortal.Repo.EnrollmentRepo;
import com.pratikshya.StudentPortal.model.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class EnrollmentImpl {
    @Autowired
    public EnrollmentRepo enrollmentRepo;

    public List<Enrollment> getEnrollment(long studentId) {
        List<Enrollment> enrollmentList = enrollmentRepo.findAll();
        return enrollmentList;
    }
    public void saveEnrollment(Enrollment enrollment) {

        enrollmentRepo.save(enrollment);
    }
}
