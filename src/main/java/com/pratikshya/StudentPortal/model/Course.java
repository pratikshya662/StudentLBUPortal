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
     private long cId;
     @Column(nullable = false, unique = true)
     private String cName;
     @Column(nullable = false, unique = true)
     private String cDuration;
     @Column(nullable = false, unique = true)
     private Long cFee;


}
