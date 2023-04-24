package com.pratikshya.StudentPortal.controller;
import com.pratikshya.StudentPortal.Repo.StudentRepo;
import com.pratikshya.StudentPortal.model.StudentAccount;
import com.pratikshya.StudentPortal.service.StudentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Component
public class Controller {
    @Autowired
    StudentImpl studentImpl;

    @GetMapping({"/", "/login"})
    public String login(Model model){
        model.addAttribute("student", new StudentAccount());
        return "login"; //return page
    }

    @PostMapping("/login")
    public String loginProcess(@ModelAttribute StudentAccount student){
        String email = student.getEmail();
        String password = student.getPassword();

        StudentAccount loginStudent = studentImpl.getEmail(email);
        if(loginStudent != null){
            if(email.equals(loginStudent.getEmail()) && password.equals(loginStudent.getPassword())){
                return "redirect:/enrollments"; // return controller api
            }
            System.out.println("=> No record exist.");
        }

        return "redirect:/login?error=true";
    }



    @GetMapping("/enrollments")
    public String enrollmentsPage(){
        return "enrollments";
    }

    @GetMapping({ "/register"})
    public String register(Model model){
        model.addAttribute("student", new StudentAccount());
        return "register"; //return page
    }

    @PostMapping("/register")
    public String registerProcess(@ModelAttribute StudentAccount student){
        studentImpl.createStudent(student);

        return "redirect:/login";
    }




}
