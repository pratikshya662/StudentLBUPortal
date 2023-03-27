package com.pratikshya.StudentPortal.service;

import Repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseImpl {
    @Autowired
    CourseRepo courseRepo;
}
