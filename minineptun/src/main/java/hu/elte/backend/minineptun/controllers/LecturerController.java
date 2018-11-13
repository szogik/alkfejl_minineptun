package hu.elte.backend.minineptun.controllers;

import hu.elte.backend.minineptun.entities.Course;
import hu.elte.backend.minineptun.entities.Lecturer;
import hu.elte.backend.minineptun.repositories.CourseRepository;
import hu.elte.backend.minineptun.repositories.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lecturers")
public class LecturerController {

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("")
    @Secured({"ROLE_LECTURER", "ROLE_ADMIN"})
    public ResponseEntity<Iterable<Lecturer>> getAll() {
        return ResponseEntity.ok(lecturerRepository.findAll());
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_LECTURER", "ROLE_ADMIN"})
    public ResponseEntity<Lecturer> getLecturerById(@PathVariable Integer id) {
        Optional<Lecturer> lecturer = lecturerRepository.findById(id);
        if (lecturer.isPresent()) {
            return ResponseEntity.ok(lecturer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/add-course")
    @Secured({"ROLE_Lecturer", "ROLE_ADMIN"})
    public ResponseEntity<Lecturer> addCourse(@PathVariable(name = "id") Integer id, @RequestParam(name = "courseId") Integer courseId) {
        Optional<Lecturer> olecturer = lecturerRepository.findById(id);
        if (!olecturer.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Course> ocourse = courseRepository.findById(courseId);
        if (!ocourse.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Lecturer lecturer = olecturer.get();
        Course course = ocourse.get();
        lecturer.getCourses().add(course);
        course.setLecturer(lecturer);
        lecturerRepository.save(lecturer);
        courseRepository.save(course);
        return ResponseEntity.ok(lecturer);
    }

    @PatchMapping("/{id}/remove-course")
    @Secured({"ROLE_LECTURER", "ROLE_ADMIN"})
    public ResponseEntity<Lecturer> removeCourse(@PathVariable(name = "id") Integer id, @RequestParam(name = "courseId") Integer courseId) {
        Optional<Lecturer> olecturer = lecturerRepository.findById(id);
        if (!olecturer.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Course> ocourse = courseRepository.findById(courseId);
        if (!ocourse.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Lecturer lecturer = olecturer.get();
        Course course = ocourse.get();
        lecturer.getCourses().remove(course);
        course.setLecturer(null);
        lecturerRepository.save(lecturer);
        courseRepository.save(course);
        return ResponseEntity.ok(lecturer);
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Lecturer> oLecturer = lecturerRepository.findById(id);
        if (oLecturer.isPresent()) {
            lecturerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
