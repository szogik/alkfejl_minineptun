package hu.elte.backend.minineptun.controllers;

import hu.elte.backend.minineptun.entities.User;
import hu.elte.backend.minineptun.repositories.UserRepository;
import hu.elte.backend.minineptun.security.AuthenticatedUser;
import hu.elte.backend.minineptun.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByUsername(user.getUsername());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(myUserDetailsService.setUserEntity(user, user.getUsername()));
    }

    @PostMapping("login")
    public ResponseEntity login() {
        return ResponseEntity.ok(authenticatedUser.getUser());
    }

    @GetMapping("/{username}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<User> findUser(@PathVariable String username) {
        Optional<User> oUser = userRepository.findByUsername(username);
        if (oUser.isPresent()) {
            return ResponseEntity.ok(oUser.get());
        }
        return ResponseEntity.ok(null);
    }
}