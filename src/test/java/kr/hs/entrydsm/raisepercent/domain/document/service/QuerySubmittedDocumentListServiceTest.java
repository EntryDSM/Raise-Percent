package kr.hs.entrydsm.raisepercent.domain.document.service;

import kr.hs.entrydsm.raisepercent.domain.document.facade.DocumentFacade;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class QuerySubmittedDocumentListServiceTest {

    @Mock
    private DocumentFacade documentFacade;

    @InjectMocks
    private QuerySubmittedDocumentListService service;

    @Test
    void 제출한_문서_리스트_불러오기() {
        //given
        given(documentFacade.querySubmittedDocumentList())
                .willReturn(new ArrayList<>());

        //when
        service.execute();
    }

}
