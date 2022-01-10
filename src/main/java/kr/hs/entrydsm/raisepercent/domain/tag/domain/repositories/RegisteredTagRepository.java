package kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.RegisteredTag;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegisteredTagRepository extends CrudRepository<RegisteredTag, UUID> {
    List<RegisteredTag> findByStudent(Student student);
    Optional<RegisteredTag> findByStudentAndTag(Student student, Tag tag);
}
