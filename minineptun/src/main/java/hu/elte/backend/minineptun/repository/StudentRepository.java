package hu.elte.backend.minineptun.repository;

import hu.elte.backend.minineptun.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {

}
