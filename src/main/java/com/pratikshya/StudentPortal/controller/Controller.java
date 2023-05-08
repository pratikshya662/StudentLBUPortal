package com.pratikshya.StudentPortal.controller;
import com.pratikshya.StudentPortal.model.Course;
import com.pratikshya.StudentPortal.model.Enrollment;
import com.pratikshya.StudentPortal.model.StudentAccount;
import com.pratikshya.StudentPortal.service.CourseImpl;
import com.pratikshya.StudentPortal.service.EnrollmentImpl;
import com.pratikshya.StudentPortal.service.StudentImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    StudentImpl studentImpl;
    @Autowired
    CourseImpl courseImpl;
    @Autowired
    EnrollmentImpl enrollmentImpl;
    @GetMapping({"/", "/login"})
    public String login(Model model){
        model.addAttribute("student", new StudentAccount());
        return "login";
    }
    @Resource(name="authenticationManager")
    private AuthenticationManager authManager;
    @PostMapping("/login")
    public @ResponseBody String loginProcess(@RequestBody StudentAccount studentAccount, HttpServletRequest request)//-- changed for student controller
    {
        String username = studentAccount.getUsername();
        String password = studentAccount.getPassword();
        StudentAccount loginStudent = studentImpl.getUsername(username);
        if (loginStudent != null) {
            if (username.equals(loginStudent.getUsername()) && password.equals(loginStudent.getPassword())) {
                //added code
                try {
                    UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(password, username);
                    Authentication auth = authManager.authenticate(authReq);
                    SecurityContext sc = SecurityContextHolder.getContext();
                    sc.setAuthentication(auth);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
                } catch (Exception e) {
                    SecurityContextHolder.getContext().setAuthentication(null);
                }
            }
            return "redirect:/profile";
        }
        return "redirect:/login?error=true";
    }
    @GetMapping({ "/profile"})
    public String profile(Authentication authentication, Model model){
        User userDetails = (User) authentication.getPrincipal(); //--
        long studentId = Long.valueOf(userDetails.getUsername()); //--
        model.addAttribute("student", studentImpl.getStudentByStudentId(studentId));
        return "profile"; //return page
    }
    @PostMapping({ "/profile"})
    public String profile(Authentication authentication, @ModelAttribute StudentAccount student){
        User userDetails = (User) authentication.getPrincipal();
        long studentId = Long.valueOf(userDetails.getUsername());
        student.setSid(studentId);
        studentImpl.updateStudent(student);
        return "redirect:/profile"; //return page
    }
    @GetMapping("/graduation")
    public ModelAndView graduationPage(Authentication authentication){
        ModelAndView mav = new ModelAndView("graduation");
        User userDetails = (User) authentication.getPrincipal();
        long studentId = Long.valueOf(userDetails.getUsername());
        mav.addObject("graduation", studentImpl.getGraduation(studentId));
        return mav;
    }
    @GetMapping("/enrollments")
    public ModelAndView  enrollmentsPage(Authentication authentication){
        ModelAndView mav = new ModelAndView("enrollments");
        User userDetails = (User) authentication.getPrincipal();
        long studentId = Long.valueOf(userDetails.getUsername());
        List<Enrollment> enrollmentList=enrollmentImpl.getEnrollment(studentId);
        for(Enrollment enrollment:enrollmentList){
            long cId=enrollment.getCid();
            Course course=courseImpl.getCourseById(String.valueOf(cId));
            enrollment.setCourseDisplay(course.getCname());
        }
        mav.addObject("enrollment",enrollmentList);
        return mav;
    }

    @GetMapping("/enrol/course/{courseid}")
    public String  enrollmentsPage(Authentication authentication, @PathVariable String courseid){
        Enrollment enrollment= new Enrollment();
        enrollment.setCid(Long.valueOf(courseid));
        User userDetails = (User) authentication.getPrincipal();
        long studentId = Long.valueOf(userDetails.getUsername());

        enrollment.setSid(studentId);

        Course course = courseImpl.getCourseById(courseid);

        enrollmentImpl.saveEnrollment(enrollment,course.getCfee());
        return "redirect:/enrollments";
    }

    @GetMapping("/courses")
    public ModelAndView  coursesPage(){
        ModelAndView mav = new ModelAndView("courses");
        mav.addObject("courses", courseImpl.getCourse());
        return mav;
    }

    @GetMapping({ "/register"})
    public String register(Model model){
        model.addAttribute("studentAccount", new StudentAccount());
        return "register"; //return page
    }

    @PostMapping("/register")
    public String registerProcess(@ModelAttribute StudentAccount studentAccount){
        studentImpl.createStudent(studentAccount);

        return "redirect:/login";
    }



}
