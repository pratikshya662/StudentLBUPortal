package com.pratikshya.StudentPortal.service;

import com.pratikshya.StudentPortal.Repo.StudentRepo;
import com.pratikshya.StudentPortal.model.StudentAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;



@Component
public class StudentImpl {
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    public StudentAccount getUsername(String username) {
        StudentAccount studentList = studentRepo.findByUsername(username);
        return studentList;
    }
    public void createStudent(StudentAccount student){
       int strNum = new RandomGenerator().nextInt();
        student.setSid((long)strNum);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        studentRepo.save(student);

        RestTemplate restTemplate = new RestTemplate();

        //create finance account
        String createFinanceAccount = "http://localhost:8081/accounts";
        Map createFinanceAccountMap= new HashMap();
        createFinanceAccountMap.put("studentId", student.getSid());
        ResponseEntity<String> createFinanceAccountResponse = restTemplate.postForEntity(createFinanceAccount,createFinanceAccountMap, String.class);

        //create library account
        String createLibraryAccount = "http://localhost/api/register";
        Map createLibraryAccountMap= new HashMap();
        createLibraryAccountMap.put("studentId", student.getSid());
        ResponseEntity<String> createLibraryAccountResponse = restTemplate.postForEntity(createLibraryAccount,createLibraryAccountMap, String.class);
    }

    class RandomGenerator {
        List<Integer> ints = new ArrayList<>();
        int i = 0;

        RandomGenerator() {
            for (int i = 0; i < 10000; i++) {
                ints.add(i);
            }
            Collections.shuffle(ints);
        }

        int nextInt() {
            return ints.get(i++);
        }
    }

    public void updateStudent (StudentAccount student){
        StudentAccount studentDb = studentRepo.findBySid(student.getSid());
        studentDb.setFname(student.getFname());
        studentDb.setLname(student.getLname());
        studentDb.setId(student.getId());
        studentDb.setPassword(passwordEncoder.encode(student.getPassword())); //--
        studentDb.setUsername(student.getUsername());
        studentRepo.save(studentDb);
    }

    public StudentAccount getStudentByStudentId(long studentId) {
        StudentAccount student = studentRepo.findBySid(studentId);
        return student;
    }

    //--
    public Boolean getGraduation(long studentId) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8081/accounts/student/"+studentId;
        ResponseEntity<Map> response = restTemplate.getForEntity(fooResourceUrl, Map.class);
        Map map = response.getBody();
        boolean hasOutstandingBalance = (boolean) map.get("hasOutstandingBalance");
        return !hasOutstandingBalance;
    }
}

