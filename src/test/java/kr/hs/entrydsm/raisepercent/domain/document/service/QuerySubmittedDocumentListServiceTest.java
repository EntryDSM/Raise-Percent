package kr.hs.entrydsm.raisepercent.domain.document.service;

import kr.hs.entrydsm.raisepercent.domain.document.facade.DocumentFacade;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuerySubmittedDocumentListServiceTest {

    private static final DocumentFacade documentFacade = mock(DocumentFacade.class);

    private static final QuerySubmittedDocumentListService service = new QuerySubmittedDocumentListService(documentFacade);

    @Test
    void 제출한_문서_리스트_불러오기() {
        when(documentFacade.querySubmittedDocumentList())
                .thenReturn(new ArrayList<>());

        service.execute();
    }

}
