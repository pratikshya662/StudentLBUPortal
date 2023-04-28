package com.pratikshya.StudentPortal.Repo;

import com.pratikshya.StudentPortal.model.StudentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentAccount, Long> {

    public StudentAccount findByUsername(String username);
    public StudentAccount findBySid(Long sid);


}
