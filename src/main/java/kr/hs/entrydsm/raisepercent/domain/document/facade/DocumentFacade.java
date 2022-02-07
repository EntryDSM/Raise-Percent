package kr.hs.entrydsm.raisepercent.domain.document.facade;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.StayDocument;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.DocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.SubmittedDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response.SubmittedDocumentElement;
import kr.hs.entrydsm.raisepercent.global.entity.BaseDocumentContentEntity;
import kr.hs.entrydsm.raisepercent.global.exception.DocumentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class DocumentFacade {

    private final DocumentRepository documentRepository;
    private final SubmittedDocumentRepository submittedDocumentRepository;

    public Document getDocument(UUID id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> DocumentNotFoundException.EXCEPTION);
    }

    public List<SubmittedDocumentElement> querySubmittedDocumentList() {
        return submittedDocumentRepository.findAll()
                .stream()
                .map(document -> SubmittedDocumentElement.builder()
                        .name(document.getStudentName())
                        .number(document.getStudentNumber())
                        .submittedDocumentId(document.getId().toString())
                        .build())
                .collect(Collectors.toList());
    }

    public boolean checkIsEmpty(List<? extends BaseDocumentContentEntity> entityList) {
        return entityList.isEmpty();
    }

}
