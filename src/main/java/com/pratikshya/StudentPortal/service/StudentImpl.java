package com.pratikshya.StudentPortal.service;

import Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class StudentImpl {
    @Autowired
    StudentRepo studentRepo;
    public void getStudent(){
      studentRepo.findAll() ;
      public void registerStudent(){
        Random random = new Random();
        long randomLong = random.nextLong();
    }
}}

