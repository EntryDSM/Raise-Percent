package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubmittedDocumentElementTest {

    private static final SubmittedDocumentElement documentElement = SubmittedDocumentElement.builder().build();

    @Test
    void 제출자_이름_가져오기() {
        String name = "홍길동";

        assertEquals(name, documentElement.getName());
    }

    @Test
    void 제출자_학번_가져오기() {
        String number = "1234";

        assertEquals(number, documentElement.getNumber());
    }

    @Test
    void 제출한_문서_아이디_가져오기() {
        String submittedDocumentId = UUID.randomUUID().toString();

        assertEquals(submittedDocumentId, documentElement.getSubmittedDocumentId());
    }

}
