package kr.hs.entrydsm.raisepercent.domain.document.domain.repositories;

import java.util.List;
import java.util.UUID;
import kr.hs.entrydsm.raisepercent.domain.document.domain.LocalDocument;
import kr.hs.entrydsm.raisepercent.global.entity.DocumentContentId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalDocumentRepository extends CrudRepository<LocalDocument, DocumentContentId> {
	List<LocalDocument> findByIdDocumentId(UUID id_document_id);
}
