package com.pratikshya.StudentPortal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private long CId;
     @Column(nullable = false, unique = true)
     private String CName;
     @Column(nullable = false, unique = true)
     private String CDuration;
     @Column(nullable = false, unique = true)
     private String CFee;


}
