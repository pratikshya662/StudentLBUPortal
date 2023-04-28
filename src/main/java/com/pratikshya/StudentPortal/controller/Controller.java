package com.pratikshya.StudentPortal.controller;
import com.pratikshya.StudentPortal.model.StudentAccount;
import com.pratikshya.StudentPortal.service.StudentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    StudentImpl studentImpl;
    private Object username;

    @GetMapping({"/", "/login"})
    public String login(Model model){
        model.addAttribute("studentAccount", new StudentAccount());
        return "pages-login"; //return page
    }

    @PostMapping("/login")
    public String loginProcess(@ModelAttribute StudentAccount student){
        String studentUsername = student.getUsername();
        String password = student.getPassword();

        StudentAccount loginStudent = studentImpl.getUsername(studentUsername);
        if(loginStudent != null){
            if (studentUsername.equals(loginStudent.getUsername()))
                if (password.equals(loginStudent.getPassword())) {
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
        model.addAttribute("studentAccount", new StudentAccount());
        return "pages-register"; //return page
    }

    @PostMapping("/register")
    public String registerProcess(@ModelAttribute StudentAccount studentAccount){
        studentImpl.createStudent(studentAccount);

        return "redirect:/login";
    }




}
