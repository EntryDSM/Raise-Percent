package kr.hs.entrydsm.raisepercent.domain.document.domain.repositories;

import kr.hs.entrydsm.raisepercent.domain.document.domain.StayDocument;
import kr.hs.entrydsm.raisepercent.global.entity.DocumentContentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StayDocumentRepository extends JpaRepository<StayDocument, DocumentContentId> {
    List<StayDocument> findByIdDocumentId(UUID uuid);
}
