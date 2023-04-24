
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
    private long sId;
    @Column(nullable = false, unique = true)
    private String fName;
    @Column(nullable = false, unique = true)
    private String lName;
    @Column(nullable = false, unique = true)
    private String country;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(nullable = false, unique = true)
    private String email; //first word small and then capital first letter

    @Column(nullable = false, unique = true)
    private Integer age;
    @Column(nullable = false, unique = true)
    private String gender;
    @Column(nullable = false, unique = true)
    private String password;


}
