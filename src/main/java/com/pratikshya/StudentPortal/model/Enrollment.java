package com.pratikshya.StudentPortal.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;


public class Enrollment {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String EId;

    @Column(nullable = false, unique = true)
    private String CId;
    @Column(nullable = false, unique = true)
    private  String SId;


}
