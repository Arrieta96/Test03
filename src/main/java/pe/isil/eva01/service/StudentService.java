package pe.isil.eva01.service;

import org.springframework.stereotype.Service;
import pe.isil.eva01.model.Student;
import pe.isil.eva01.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements BaseService<Student, String> {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(String documentNumber) {
        return studentRepository.findById(documentNumber);
    }
}
