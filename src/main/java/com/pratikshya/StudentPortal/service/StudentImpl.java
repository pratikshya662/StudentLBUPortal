package com.pratikshya.StudentPortal.service;

import com.pratikshya.StudentPortal.Repo.StudentRepo;
import com.pratikshya.StudentPortal.model.StudentAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class StudentImpl {
    @Autowired
    StudentRepo studentRepo;

    public StudentAccount getUsername(String username) {
        StudentAccount studentList = studentRepo.findByUsername(username);
        return studentList;
    }
    public void createStudent(StudentAccount student){
        Random rd = new Random();
        student.setSid(rd.nextLong());
        studentRepo.save(student);
    }
    public void updateStudent (StudentAccount student){
        studentRepo.save(student);
    }


}

