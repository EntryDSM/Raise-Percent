package kr.hs.entrydsm.raisepercent.domain.document.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.document.domain.StayDocument;
import kr.hs.entrydsm.raisepercent.global.entity.DocumentContentId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StayDocumentRepository extends CrudRepository<StayDocument, DocumentContentId> {
}
