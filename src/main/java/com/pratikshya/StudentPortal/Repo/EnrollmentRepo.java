package com.pratikshya.StudentPortal.Repo;

import com.pratikshya.StudentPortal.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {
   List<Enrollment> findBySid(Long sId);



}
