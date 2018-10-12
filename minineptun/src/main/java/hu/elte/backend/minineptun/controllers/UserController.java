package hu.elte.backend.minineptun.controllers;

import hu.elte.backend.minineptun.entities.User;
import hu.elte.backend.minineptun.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByUsername(user.getUsername());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findUser(@RequestParam String username) {
        Optional<User> oUser = userRepository.findByUsername(username);
        if (oUser.isPresent()) {
            return ResponseEntity.ok(oUser.get());
        }
        return ResponseEntity.ok(null);
    }
}