package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubmittedDocumentListResponseTest {

    private static final List<SubmittedDocumentElement> documentElements = new ArrayList<>();

    private static final SubmittedDocumentListResponse response = new SubmittedDocumentListResponse(documentElements);

    @Test
    void 제출한_문서_리스트_가져오기() {
        assertEquals(documentElements, response.getDocumentList());
    }

}
