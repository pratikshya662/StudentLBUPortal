package Repo;

import com.pratikshya.StudentPortal.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepo extends JpaRepository<CourseRepo, Long> {

    public List<Course> findByCourseName(String courseName);
}
