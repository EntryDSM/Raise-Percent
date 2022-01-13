package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubmittedDocumentElementTest {

    private static final String name = "홍길동";

    private static final String number = "1234";

    private static final String submittedDocumentId = UUID.randomUUID().toString();

    private static final SubmittedDocumentElement documentElement = SubmittedDocumentElement.builder()
            .name(name)
            .number(number)
            .submittedDocumentId(submittedDocumentId)
            .build();

    @Test
    void 제출자_이름_가져오기() {
        assertEquals(name, documentElement.getName());
    }

    @Test
    void 제출자_학번_가져오기() {
        assertEquals(number, documentElement.getNumber());
    }

    @Test
    void 제출한_문서_아이디_가져오기() {
        assertEquals(submittedDocumentId, documentElement.getSubmittedDocumentId());
    }

}
