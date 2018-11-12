package hu.elte.backend.minineptun.repositories;

import hu.elte.backend.minineptun.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {
    Optional<Student> findByName(String name);
}
