package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class SubmittedDocumentElementTest {

    private final String name = "홍길동";

    private final String number = "1234";

    private final String submittedDocumentId = UUID.randomUUID().toString();

    private final SubmittedDocumentElement documentElement = SubmittedDocumentElement.builder()
            .name(name)
            .number(number)
            .submittedDocumentId(submittedDocumentId)
            .build();

    @Test
    void 제출자_이름_가져오기() {
        assertThat(name).isEqualTo(documentElement.getName());
    }

    @Test
    void 제출자_학번_가져오기() {
        assertThat(number).isEqualTo(documentElement.getNumber());
    }

    @Test
    void 제출한_문서_아이디_가져오기() {
        assertThat(submittedDocumentId).isEqualTo(documentElement.getSubmittedDocumentId());
    }

}
