package kr.hs.entrydsm.raisepercent.domain.document.facade;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.DocumentUserConstant;
import kr.hs.entrydsm.raisepercent.domain.document.domain.StayDocument;
import kr.hs.entrydsm.raisepercent.domain.document.domain.SubmittedDocument;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.DocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.SubmittedDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response.SubmittedDocumentElement;
import kr.hs.entrydsm.raisepercent.global.exception.DocumentNotFoundException;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DocumentFacadeTest {

    private static final UUID id = UUIDUtil.convertToUUID("550e8400-e29b-41d4-a716-446655440000");

    private static final DocumentRepository documentRepository = mock(DocumentRepository.class);

    private static final SubmittedDocumentRepository submittedDocumentRepository = mock(SubmittedDocumentRepository.class);

    private static final DocumentFacade documentFacade = new DocumentFacade(documentRepository, submittedDocumentRepository);

    private static final SubmittedDocument submittedDocument = mock(SubmittedDocument.class);

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

    @Test
    void 제출한_문서_리스트_가져오기() {
        List<SubmittedDocument> documents = new ArrayList<>();

        String name = DocumentUserConstant.name;
        String number = DocumentUserConstant.number;

        when(submittedDocument.getStudentName())
                .thenReturn(name);

        when(submittedDocument.getStudentNumber())
                .thenReturn(number);

        documents.add(submittedDocument);

        when(submittedDocumentRepository.findAll())
                .thenReturn(documents);

        when(submittedDocument.getId())
                .thenReturn(id);

        List<SubmittedDocumentElement> retDocumentElements = documentFacade.querySubmittedDocumentList();

        retDocumentElements.forEach(element -> {
            assertThat(element.getName()).isEqualTo(name);
            assertThat(element.getNumber()).isEqualTo(number);
            assertThat(element.getSubmittedDocumentId()).isEqualTo(id.toString());
        });
    }

    @Test
    void 문서_비어있음() {
        List<StayDocument> documentList = new ArrayList<>();

        assertThrows(DocumentNotFoundException.class, () -> documentFacade.assertNotEmpty(documentList));
    }

    @Test
    void 문서_비어있지_않음() {
        List<StayDocument> documentList = List.of(StayDocument.builder().build());

        documentFacade.assertNotEmpty(documentList);
    }

}
