
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
    private long sid;
    @Column(nullable = false)
    private String fname;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String lname;

    @Column(nullable = false, unique = true)
    private String email; //first word small and then capital first letter

    @Column(nullable = false)
    private String password;


}
