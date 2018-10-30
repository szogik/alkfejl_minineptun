package hu.elte.backend.minineptun.controllers;

import hu.elte.backend.minineptun.entities.Lecturer;
import hu.elte.backend.minineptun.entities.Subject;
import hu.elte.backend.minineptun.repositories.LecturerRepository;
import hu.elte.backend.minineptun.repositories.SubjectRepository;
import hu.elte.backend.minineptun.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private UserDetailsServiceImpl userService;

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

    @PatchMapping("/add-lecturer")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Object> addLecturer(@RequestParam Integer lecturerId, @RequestParam Integer subjectId){
        Lecturer lecturer = lecturerRepository.findById(lecturerId).get();
        if(lecturer == null){
            return ResponseEntity.badRequest().build();
        }
        Subject subject = subjectRepository.findById(subjectId).get();
        if(subject == null){
            return ResponseEntity.badRequest().build();
        }
        subject.getLecturers().add(lecturer);
        return ResponseEntity.ok().build();
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
