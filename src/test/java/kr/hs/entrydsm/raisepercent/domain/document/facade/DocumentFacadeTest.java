package kr.hs.entrydsm.raisepercent.domain.document.facade;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.DocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.SubmittedDocumentRepository;
import kr.hs.entrydsm.raisepercent.global.exception.DocumentNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DocumentFacadeTest {

    private static final UUID id = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");

    private static final DocumentRepository documentRepository = mock(DocumentRepository.class);

    private static final SubmittedDocumentRepository submittedDocumentRepository = mock(SubmittedDocumentRepository.class);

    private static final DocumentFacade documentFacade = new DocumentFacade(documentRepository, submittedDocumentRepository);

    @Test
    void 문서_정보_가져오기() {
        Document document = Document.builder().build();

        when(documentRepository.findById(id))
                .thenReturn(Optional.of(document));

        Document retDocument = documentFacade.getDocument(id);

        assertEquals(document, retDocument);
    }

    @Test
    void 문서_정보_없음() {
        when(documentRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(DocumentNotFoundException.class, () -> documentFacade.getDocument(any()));
    }

}
