package kr.hs.entrydsm.raisepercent.domain.teacher.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, String> {
}
