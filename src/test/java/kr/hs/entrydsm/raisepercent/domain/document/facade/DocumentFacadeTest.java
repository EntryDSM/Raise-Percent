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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentFacadeTest {

    private static final UUID id = UUIDUtil.convertToUUID("550e8400-e29b-41d4-a716-446655440000");

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private SubmittedDocumentRepository submittedDocumentRepository;

    @InjectMocks
    private DocumentFacade documentFacade;

    @Mock
    private SubmittedDocument submittedDocument;

    @Test
    void 문서_정보_가져오기() {
        //given
        Document document = Document.builder().build();

        given(documentRepository.findById(id))
                .willReturn(Optional.of(document));

        //when
        Document retDocument = documentFacade.getDocument(id);

        //then
        assertEquals(document, retDocument);
    }

    @Test
    void 문서_정보_없음() {
        //given
        given(documentRepository.findById(id))
                .willReturn(Optional.empty());

        //when then
        assertThrows(DocumentNotFoundException.class, () -> documentFacade.getDocument(id));
    }

    @Test
    void 제출한_문서_리스트_가져오기() {
        //given
        List<SubmittedDocument> documents = new ArrayList<>();

        String name = DocumentUserConstant.name;
        String number = DocumentUserConstant.number;

        given(submittedDocument.getStudentName())
                .willReturn(name);

        given(submittedDocument.getStudentNumber())
                .willReturn(number);

        documents.add(submittedDocument);

        given(submittedDocumentRepository.findAll())
                .willReturn(documents);

        given(submittedDocument.getId())
                .willReturn(id);

        //when
        List<SubmittedDocumentElement> retDocumentElements = documentFacade.querySubmittedDocumentList();

        //then
        retDocumentElements.forEach(element -> {
            assertThat(element.getName()).isEqualTo(name);
            assertThat(element.getNumber()).isEqualTo(number);
            assertThat(element.getSubmittedDocumentId()).isEqualTo(id.toString());
        });
    }

    @Test
    void 문서_비어있음() {
        //given
        List<StayDocument> documentList = new ArrayList<>();

        //when then
        assertThrows(DocumentNotFoundException.class, () -> documentFacade.assertNotEmpty(documentList));
    }

    @Test
    void 문서_비어있지_않음() {
        //given
        List<StayDocument> documentList = List.of(StayDocument.builder().build());

        //when then
        documentFacade.assertNotEmpty(documentList);
    }

}
