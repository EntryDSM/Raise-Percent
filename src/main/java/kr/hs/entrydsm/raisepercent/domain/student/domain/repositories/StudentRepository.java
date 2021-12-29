package kr.hs.entrydsm.raisepercent.domain.student.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
}
