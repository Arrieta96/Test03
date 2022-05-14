package pe.isil.eva01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.isil.eva01.model.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
}
