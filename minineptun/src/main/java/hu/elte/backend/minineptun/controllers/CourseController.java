package hu.elte.backend.minineptun.controllers;


import hu.elte.backend.minineptun.entities.Course;
import hu.elte.backend.minineptun.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Course>> getAll() {
        return ResponseEntity.ok(courseRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> get(@PathVariable Integer id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            return ResponseEntity.ok(course.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Course> post(@RequestBody Course course) {
        Course savedCourse = courseRepository.save(course);
        return ResponseEntity.ok(savedCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> put(@RequestBody Course course, @PathVariable Integer id) {
        Optional<Course> oCourse = courseRepository.findById(id);
        if (oCourse.isPresent()) {
            //course.setId(id);
            return ResponseEntity.ok(courseRepository.save(course));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Course> oCourse = courseRepository.findById(id);
        if (oCourse.isPresent()) {
            courseRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
