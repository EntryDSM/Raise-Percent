package kr.hs.entrydsm.raisepercent.domain.document.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.document.domain.SubmittedDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubmittedDocumentRepository extends JpaRepository<SubmittedDocument, UUID> {
}
