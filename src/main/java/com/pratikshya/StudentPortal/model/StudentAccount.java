
package com.pratikshya.StudentPortal.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, unique = true)
    private String SId;
    @Column(nullable = false, unique = true)
    private String FName;
    @Column(nullable = false, unique = true)
    private String LName;
    @Column(nullable = false, unique = true)
    private String Country;
    @Column(nullable = false, unique = true)
    private String Phone;
    @Column(nullable = false, unique = true)
    private String Email;
    @Column(nullable = false, unique = true)
    private Integer Age;
    @Column(nullable = false, unique = true)
    private String Gender;


}
