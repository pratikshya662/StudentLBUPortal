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

    @Column(nullable = false, unique = true)
    private long cid;
    @Column(nullable = false, unique = true)
    private  long sid;


}
