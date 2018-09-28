package hu.elte.backend.minineptun.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.security.auth.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject,Integer> {
}
