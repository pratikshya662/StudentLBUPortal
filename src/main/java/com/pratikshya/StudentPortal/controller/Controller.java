package com.pratikshya.StudentPortal.controller;
import com.pratikshya.StudentPortal.model.StudentAccount;
import com.pratikshya.StudentPortal.service.CourseImpl;
import com.pratikshya.StudentPortal.service.EnrollmentImpl;
import com.pratikshya.StudentPortal.service.StudentImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//@RestController//-- next controlller use it
//@CrossOrigin
//@RequestMapping

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

}
    @Resource(name="authenticationManager")
    private AuthenticationManager authManager;


    @PostMapping("/login")
    public @ResponseBody String loginProcess(@RequestBody StudentAccount studentAccount, HttpServletRequest request)//-- changed for student controller {
    String username = studentAccount.getUsername();
    String password = studentAccount.getPassword();
    StudentAccount loginStudent = studentImpl.getUsername(username);
        if(loginStudent != null){
        if(username.equals(loginStudent.getUsername()) && password.equals(loginStudent.getPassword())){
            //added code
            try {
                UsernamePasswordAuthenticationToken authReq =new UsernamePasswordAuthenticationToken(password, username);
                Authentication auth = authManager.authenticate(authReq);
                SecurityContext sc = SecurityContextHolder.getContext();
                sc.setAuthentication(auth);
                HttpSession session = request.getSession(true);
                session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
            } catch (Exception e) {
                SecurityContextHolder.getContext().setAuthentication(null);
            }