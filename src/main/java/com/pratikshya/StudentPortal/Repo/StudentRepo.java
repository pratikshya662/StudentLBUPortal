package com.pratikshya.StudentPortal.Repo;

import com.pratikshya.StudentPortal.model.StudentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentAccount, Long> {
    public StudentAccount findByFName(String fName);
    public StudentAccount findByEmail(String email);
    public StudentAccount findBySId(Long sId);


}
