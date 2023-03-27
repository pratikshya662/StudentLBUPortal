package Repo;

import com.pratikshya.StudentPortal.model.StudentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<StudentAccount, Long> {

}
