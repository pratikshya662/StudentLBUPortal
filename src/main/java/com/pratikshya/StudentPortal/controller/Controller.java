package com.pratikshya.StudentPortal.controller;

import Repo.StudentRepo;
import com.pratikshya.StudentPortal.service.CourseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class Controller {
    @Autowired
    StudentRepo studentRepo;
    public  String login(Model model){
        model.addAllAttributes()
    }




}
