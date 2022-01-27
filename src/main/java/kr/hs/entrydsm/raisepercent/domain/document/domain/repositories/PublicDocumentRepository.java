package kr.hs.entrydsm.raisepercent.domain.document.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.document.domain.PublicDocument;
import kr.hs.entrydsm.raisepercent.global.entity.DocumentContentId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicDocumentRepository extends CrudRepository<PublicDocument, DocumentContentId> {
}
