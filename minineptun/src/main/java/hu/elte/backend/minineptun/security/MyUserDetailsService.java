package hu.elte.backend.minineptun.security;

import hu.elte.backend.minineptun.entities.Lecturer;
import hu.elte.backend.minineptun.entities.Student;
import hu.elte.backend.minineptun.entities.User;
import hu.elte.backend.minineptun.repositories.LecturerRepository;
import hu.elte.backend.minineptun.repositories.StudentRepository;
import hu.elte.backend.minineptun.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> oUser = userRepository.findByUsername(username);
        if (!oUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        User user = oUser.get();
        authenticatedUser.setUser(user);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    public User setUserEntity(User user, String fullName) {

        if (user.getRole().equals(User.Role.ROLE_STUDENT)) {
            Student student = new Student(new HashSet<>());
            studentRepository.save(student);
            student.setName(fullName);
            student.setUser(user);

        } else if (user.getRole().equals(User.Role.ROLE_LECTURER)) {
            Lecturer lecturer = new Lecturer(new HashSet<>(), new HashSet<>());
            lecturerRepository.save(lecturer);
            lecturer.setName(user.getUsername());
            lecturer.setUser(user);
        }
        return userRepository.save(user);
    }
}