package hu.elte.backend.minineptun.repositories;

import hu.elte.backend.minineptun.entities.Lecturer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LecturerRepository extends CrudRepository<Lecturer,Integer> {
    Optional<Lecturer> findByName(String name);
}
