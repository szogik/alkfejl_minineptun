package hu.elte.backend.minineptun.controllers;


import hu.elte.backend.minineptun.entities.Course;
import hu.elte.backend.minineptun.repositories.CourseRepository;
import hu.elte.backend.minineptun.repositories.LecturerRepository;
import hu.elte.backend.minineptun.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private UserDetailsServiceImpl userService;

    @GetMapping("")
    @Secured({"ROLE_STUDENT", "ROLE_LECTURER", "ROLE_ADMIN"})
    public ResponseEntity<Iterable<Course>> getAll() {
        return ResponseEntity.ok(courseRepository.findAll());
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_STUDENT", "ROLE_LECTURER", "ROLE_ADMIN"})
    public ResponseEntity<Course> getCourses(@PathVariable Integer id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            return ResponseEntity.ok(course.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Course> postCourse(@RequestBody Course course) {
        Course savedCourse = courseRepository.save(course);
        return ResponseEntity.ok(savedCourse);
    }

    @PatchMapping("/{id}")
    @Secured({"ROLE_LECTURER", "ROLE_ADMIN"})
    public ResponseEntity<Course> patchCourse(@RequestParam(required = false) Course.CourseType type,
                                              @RequestParam(required = false) String time,
                                              @RequestParam(required = false) String location,
                                              @PathVariable Integer id) {
        Optional<Course> oCourse = courseRepository.findById(id);
        if (oCourse.isPresent()) {
            Course course = oCourse.get();
            if (type != null)
                course.setType(type);
            if (time != null)
                course.setTime(time);
            if (location != null)
                course.setLocation(location);
            return ResponseEntity.ok(courseRepository.save(course));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity deleteCourse(@PathVariable Integer id) {
        Optional<Course> oCourse = courseRepository.findById(id);
        if (oCourse.isPresent()) {
            courseRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
