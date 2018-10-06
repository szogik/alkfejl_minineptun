package hu.elte.backend.minineptun.controllers;

import hu.elte.backend.minineptun.entities.Lecturer;
import hu.elte.backend.minineptun.repositories.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lectures")
public class LecturerController {

    @Autowired
    private LecturerRepository lecturerRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Lecturer>> getAll() {
        return ResponseEntity.ok(lecturerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lecturer> get(@PathVariable Integer id) {
        Optional<Lecturer> lecturer = lecturerRepository.findById(id);
        if (lecturer.isPresent()) {
            return ResponseEntity.ok(lecturer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Lecturer> post(@RequestBody Lecturer lecturer) {
        Lecturer savedLecturer = lecturerRepository.save(lecturer);
        return ResponseEntity.ok(savedLecturer);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Lecturer> put(@RequestParam String name, @PathVariable Integer id) {
        Optional<Lecturer> oLecturer = lecturerRepository.findById(id);
        if (oLecturer.isPresent()) {
            Lecturer lecturer = oLecturer.get();
            if (name != null) {
                lecturer.setName(name);
            }
            return ResponseEntity.ok(lecturer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
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
