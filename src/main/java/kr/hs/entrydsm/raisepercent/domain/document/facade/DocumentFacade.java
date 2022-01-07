package kr.hs.entrydsm.raisepercent.domain.document.facade;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.DocumentRepository;
import kr.hs.entrydsm.raisepercent.global.exception.DocumentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class DocumentFacade {

    private final DocumentRepository documentRepository;

    public Document getDocument(UUID id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> DocumentNotFoundException.EXCEPTION);
    }
}
