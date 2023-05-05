package com.pratikshya.StudentPortal.controller;
import com.pratikshya.StudentPortal.model.StudentAccount;
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

@RestController //-- next controlller use it
@CrossOrigin
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentImpl studentImpl;
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
                    return "error";
                }
            }
            return "success";
        }
        return "Error";
    }
    @GetMapping({ "/profile"})
    public @ResponseBody StudentAccount profile(@RequestBody Authentication authentication){
        User userDetails = (User) authentication.getPrincipal(); //--
        long studentId = Long.valueOf(userDetails.getUsername()); //--
        return studentImpl.getStudentByStudentId(studentId); //return page
    }
    @PostMapping({ "/profile"})
    public @ResponseBody String profile(Authentication authentication, @RequestBody StudentAccount student){
        User userDetails = (User) authentication.getPrincipal();
        long studentId = Long.valueOf(userDetails.getUsername());
        student.setSid(studentId);
        studentImpl.updateStudent(student);
        return "success"; //return page
    }
    @GetMapping("/graduation")
    public @ResponseBody Boolean graduationPage(@RequestBody Authentication authentication){
        User userDetails = (User) authentication.getPrincipal();
        long studentId = Long.valueOf(userDetails.getUsername());
        return studentImpl.getGraduation(studentId);
    }
    @GetMapping({ "/register"})
    public @ResponseBody String register(@RequestBody Model model){
        model.addAttribute("studentAccount", new StudentAccount());
        return "register sucessfull"; //return page
    }

    @PostMapping("/register")
    public @ResponseBody String registerProcess(@RequestBody StudentAccount studentAccount){
        studentImpl.createStudent(studentAccount);

        return "register unsucessfull";
    }
}
