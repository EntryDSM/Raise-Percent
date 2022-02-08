package kr.hs.entrydsm.raisepercent.domain.document.domain.repositories;

import java.util.UUID;
import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.PublicDocument;
import kr.hs.entrydsm.raisepercent.global.entity.DocumentContentId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicDocumentRepository extends CrudRepository<PublicDocument, DocumentContentId> {
	void deleteByIdDocument(Document document);
}
