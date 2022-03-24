package kr.hs.entrydsm.raisepercent.domain.bookmark.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.bookmark.domain.Bookmark;
import kr.hs.entrydsm.raisepercent.domain.hr.domain.Hr;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookmarkRepository extends CrudRepository<Bookmark, UUID> {

    List<Bookmark> findByStudent(Student student);

    Optional<Bookmark> findByHrAndStudentEmail(Hr hr, String studentEmail);

}
