package com.pratikshya.StudentPortal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eid;

    @Column(nullable = false)
    private long cid;
    @Column(nullable = false)
    private  long sid;
   @Transient
    private String courseDisplay;


}
