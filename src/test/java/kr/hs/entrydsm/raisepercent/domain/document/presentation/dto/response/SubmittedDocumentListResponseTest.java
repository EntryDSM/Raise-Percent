package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SubmittedDocumentListResponseTest {

    private final List<SubmittedDocumentElement> documentElements = new ArrayList<>();

    private final SubmittedDocumentListResponse response = new SubmittedDocumentListResponse(documentElements);

    @Test
    void 제출한_문서_리스트_가져오기() {
        assertThat(documentElements).isEqualTo(response.getDocumentList());
    }

}
