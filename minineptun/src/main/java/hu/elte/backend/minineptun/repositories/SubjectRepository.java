package hu.elte.backend.minineptun.repositories;

import hu.elte.backend.minineptun.entities.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject,Integer> {
}
