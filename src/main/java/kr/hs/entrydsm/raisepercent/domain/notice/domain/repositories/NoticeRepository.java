package kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.Notice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, UUID> {
    List<Notice> findAllBy();
}
