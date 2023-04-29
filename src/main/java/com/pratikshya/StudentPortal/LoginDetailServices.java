package com.pratikshya.StudentPortal;

import com.pratikshya.StudentPortal.Repo.StudentRepo;
import com.pratikshya.StudentPortal.model.StudentAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LoginDetailServices implements UserDetailsService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public User loadUserByUsername(String username) {
        StudentAccount user =studentRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(
                String.valueOf(user.getSid()),
                user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority("Student")) //change in two place

        );
    }

}
