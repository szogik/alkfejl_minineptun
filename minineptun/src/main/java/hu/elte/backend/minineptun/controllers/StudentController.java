package hu.elte.backend.minineptun.controllers;

import hu.elte.backend.minineptun.entities.Course;
import hu.elte.backend.minineptun.entities.Student;
import hu.elte.backend.minineptun.repositories.CourseRepository;
import hu.elte.backend.minineptun.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("")
    @Secured({"ROLE_LECTURER", "ROLE_ADMIN"})
    public ResponseEntity<Iterable<Student>> getAll() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_LECTURER", "ROLE_ADMIN"})
    public ResponseEntity<Student> get(@PathVariable Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Student> post(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    @PatchMapping("/{id}/add-course")
    @Secured("ROLE_STUDENT,ROLE_ADMIN")
    public ResponseEntity<Student> addCourse(@PathVariable Integer studentId, @RequestParam Integer courseId) {
        Course course = courseRepository.findById(courseId).get();
        if (course == null) {
            return ResponseEntity.badRequest().build();
        }
        Student student = studentRepository.findById(studentId).get();
        if (student == null) {
            return ResponseEntity.badRequest().build();
        }
        student.getCourses().add(course);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
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
