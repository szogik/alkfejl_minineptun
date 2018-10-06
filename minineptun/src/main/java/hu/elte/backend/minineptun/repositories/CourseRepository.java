package hu.elte.backend.minineptun.repositories;

import hu.elte.backend.minineptun.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course,Integer> {
}
