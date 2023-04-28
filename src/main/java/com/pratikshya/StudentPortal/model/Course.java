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
     private long cid;
     @Column(nullable = false, unique = true)
     private String cname;
     @Column(nullable = false, unique = true)
     private String cduration;
     @Column(nullable = false, unique = true)
     private Long cfee;


}
