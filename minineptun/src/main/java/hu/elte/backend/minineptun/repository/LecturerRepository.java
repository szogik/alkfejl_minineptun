package hu.elte.backend.minineptun.repository;

import hu.elte.backend.minineptun.entities.Lecturer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends CrudRepository<Lecturer,Integer> {

}
