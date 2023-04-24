package com.pratikshya.StudentPortal.Repo;

import com.pratikshya.StudentPortal.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CourseRepo extends JpaRepository<Course, Long> {
    public List<Course> findByCName(String name);
}
