package hu.elte.backend.minineptun.controllers;

import hu.elte.backend.minineptun.entities.Course;
import hu.elte.backend.minineptun.entities.Lecturer;
import hu.elte.backend.minineptun.entities.Subject;
import hu.elte.backend.minineptun.repositories.CourseRepository;
import hu.elte.backend.minineptun.repositories.LecturerRepository;
import hu.elte.backend.minineptun.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("")
    @Secured({"ROLE_STUDENT", "ROLE_LECTURER", "ROLE_ADMIN"})
    public ResponseEntity<Iterable<Subject>> getAll() {
        return ResponseEntity.ok(subjectRepository.findAll());
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_STUDENT", "ROLE_LECTURER", "ROLE_ADMIN"})
    public ResponseEntity<Subject> get(@PathVariable Integer id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            return ResponseEntity.ok(subject.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Subject> post(@RequestBody Subject subject) {
        Subject savedSubject = subjectRepository.save(subject);
        return ResponseEntity.ok(savedSubject);
    }

    @PatchMapping("/{subjectId}/add-lecturer")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Subject> addLecturer(@RequestParam Integer lecturerId, @PathVariable Integer subjectId) {
        Optional<Lecturer> olecturer = lecturerRepository.findById(lecturerId);
        if (!olecturer.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Subject> osubject = subjectRepository.findById(subjectId);
        if (!osubject.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Lecturer lecturer = olecturer.get();
        Subject subject = osubject.get();
        subject.getLecturers().add(lecturer);
        lecturer.getSubjects().add(subject);
        lecturerRepository.save(lecturer);
        subjectRepository.save(subject);
        return ResponseEntity.ok(subject);
    }

    @PatchMapping("/{subjectId}/remove-lecturer")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Subject> removeLecturer(@RequestParam Integer lecturerId, @PathVariable Integer subjectId) {
        Optional<Lecturer> olecturer = lecturerRepository.findById(lecturerId);
        if (!olecturer.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Subject> osubject = subjectRepository.findById(subjectId);
        if (!osubject.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Lecturer lecturer = olecturer.get();
        Subject subject = osubject.get();
        subject.getLecturers().remove(lecturer);
        lecturer.getSubjects().remove(subject);
        lecturerRepository.save(lecturer);
        subjectRepository.save(subject);
        return ResponseEntity.ok(subject);
    }

    @PatchMapping("/{subjectId}/add-course")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Course> addCourse(@RequestParam Integer courseId, @PathVariable Integer subjectId) {
        Optional<Course> ocourse = courseRepository.findById(courseId);
        if (!ocourse.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Subject> osubject = subjectRepository.findById(subjectId);
        if (!osubject.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Course course = ocourse.get();
        Subject subject = osubject.get();
        subject.getCourses().add(course);
        course.setSubject(subject);
        subjectRepository.save(subject);
        courseRepository.save(course);
        return ResponseEntity.ok(course);
    }

    @PatchMapping("/{subjectId}/remove-course")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Course> removeCourse(@RequestParam Integer courseId, @PathVariable Integer subjectId) {
        Optional<Course> ocourse = courseRepository.findById(courseId);
        if (!ocourse.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Subject> osubject = subjectRepository.findById(subjectId);
        if (!osubject.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Course course = ocourse.get();
        Subject subject = osubject.get();
        if (!subject.getCourses().contains(course) || !course.getSubject().equals(subject)) {
            return ResponseEntity.badRequest().build();
        }
        subject.getCourses().remove(course);
        course.setSubject(null);
        subjectRepository.save(subject);
        courseRepository.save(course);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Subject> oSubject = subjectRepository.findById(id);
        if (oSubject.isPresent()) {
            subjectRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
