package hu.elte.backend.minineptun.controllers;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import hu.elte.backend.minineptun.entities.Lecturer;
import hu.elte.backend.minineptun.entities.Student;
import hu.elte.backend.minineptun.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Student>> getAll() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> get(@PathVariable Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Student> post(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> put(@RequestBody Student student, @PathVariable Integer id) {
        Optional<Student> oStudent = studentRepository.findById(id);
        if (oStudent.isPresent()) {
            //student.setId(id);
            return ResponseEntity.ok(studentRepository.save(student));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Student> oStudent = studentRepository.findById(id);
        if (oStudent.isPresent()) {
            studentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
