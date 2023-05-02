package com.pratikshya.StudentPortal.service;
import com.pratikshya.StudentPortal.Repo.EnrollmentRepo;
import com.pratikshya.StudentPortal.model.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.put;

@Component
public class EnrollmentImpl {
    @Autowired
    public EnrollmentRepo enrollmentRepo;

    public List<Enrollment> getEnrollment(long studentId) {
        List<Enrollment> enrollmentList = enrollmentRepo.findAll();
        return enrollmentList;
    }
    public void saveEnrollment(Enrollment enrollment,float cfee) {

        enrollmentRepo.save(enrollment);
        RestTemplate restTemplate = new RestTemplate();

        String cI= "http://localhost:8081/invoices";
        Map cIMap = new HashMap();
        cIMap.put("amount",cfee+"");
        cIMap.put("type","tution_fee");
        cIMap.put("dueDate","2024-01-24");

        Map cIstudebtMap=new HashMap();
        cIstudebtMap.put("studentId",enrollment.getSid());
        cIMap.put("account",cIstudebtMap);
        ResponseEntity<String> cLibraryAccResponse = restTemplate.postForEntity(cI,cIMap,String.class);


    }
}
