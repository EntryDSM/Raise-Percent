package kr.hs.entrydsm.raisepercent.domain.document.service;

import kr.hs.entrydsm.raisepercent.domain.document.domain.StayDocument;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.PublicDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.StayDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.facade.DocumentFacade;
import kr.hs.entrydsm.raisepercent.global.exception.DocumentNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ApproveStayDocumentServiceTest {

    @Mock
    private DocumentFacade documentFacade;

    @Mock
    private StayDocumentRepository stayDocumentRepository;

    @Mock
    private PublicDocumentRepository publicDocumentRepository;

    @InjectMocks
    private ApproveStayDocumentService service;

    @Test
    void 대기문서_승인_성공() {
        //given
        List<StayDocument> stayDocumentList = List.of(StayDocument.builder().build());

        given(stayDocumentRepository.findByIdDocumentId(any()))
                .willReturn(stayDocumentList);

        willDoNothing().given(documentFacade).assertNotEmpty(stayDocumentList);

        //when
        service.execute("123e4567-e89b-12d3-a456-426614174000");

        //then
        then(publicDocumentRepository).should(times(1)).saveAll(any());
        then(stayDocumentRepository).should(times(1)).deleteAll(any());
    }

    @Test
    void 문서_존재하지_않음_예외() {
        //given
        List<StayDocument> documentList = new ArrayList<>();

        given(stayDocumentRepository.findByIdDocumentId(any()))
                .willReturn(documentList);

        willThrow(DocumentNotFoundException.class).given(documentFacade).assertNotEmpty(documentList);

        //when then
        assertThrows(DocumentNotFoundException.class, () -> service.execute("123e4567-e89b-12d3-a456-426614174000"));
    }

}
