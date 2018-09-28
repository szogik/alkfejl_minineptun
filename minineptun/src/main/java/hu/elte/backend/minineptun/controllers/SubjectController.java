package hu.elte.backend.minineptun.controllers;


import hu.elte.backend.minineptun.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import java.util.Optional;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Subject>> getAll() {
        return ResponseEntity.ok(subjectRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> get(@PathVariable Integer id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            return ResponseEntity.ok(subject.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Subject> post(@RequestBody Subject subject) {
        Subject savedSubject = subjectRepository.save(subject);
        return ResponseEntity.ok(savedSubject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> put(@RequestBody Subject subject, @PathVariable Integer id) {
        Optional<Subject> oSubject = subjectRepository.findById(id);
        if (oSubject.isPresent()) {
            //subject.setId(id);
            return ResponseEntity.ok(subjectRepository.save(subject));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
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
