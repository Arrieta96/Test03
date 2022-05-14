package pe.isil.eva01.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.isil.eva01.model.Student;
import pe.isil.eva01.service.StudentService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class StudentResource {

    private final StudentService studentService;

    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAll(){

        List<Student> studentList = studentService.getAll();
        if (studentList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/students/{documentNumber}")
    public ResponseEntity<Student> getByDocumentNumber(@PathVariable String documentNumber){

        return studentService.findById(documentNumber)
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/students")
    public ResponseEntity<Student> save(@RequestBody Student student){
        Student studentSaved = studentService.save(student);
        return new ResponseEntity<>(studentSaved, HttpStatus.CREATED);
    }

    @PutMapping("/students/{documentNumber}")
    public ResponseEntity<Student> update(@RequestBody Student student,
                                          @PathVariable String documentNumber){

        return studentService.findById(documentNumber)
                .map(currentStudent -> {

                    currentStudent.setFirstName(student.getFirstName());
                    currentStudent.setLastName(student.getLastName());
                    currentStudent.getAddress().setStreet(student.getAddress().getStreet());
                    currentStudent.getAddress().setCity(student.getAddress().getCity());
                    currentStudent.getAddress().setCountry(student.getAddress().getCountry());
                    currentStudent.setTotalCredits(student.getTotalCredits());

                    Student studentUpdated = studentService.update(currentStudent);
                    return new ResponseEntity<>(studentUpdated, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/students/{documentNumber}")
    public ResponseEntity<?> delete(@PathVariable String documentNumber){

        return studentService.findById(documentNumber)
                .map(student -> {
                    studentService.delete(student);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
