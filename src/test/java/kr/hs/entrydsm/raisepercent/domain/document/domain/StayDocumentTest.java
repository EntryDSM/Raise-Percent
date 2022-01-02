package kr.hs.entrydsm.raisepercent.domain.document.domain;

import kr.hs.entrydsm.raisepercent.global.entity.DocumentContentId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StayDocumentTest {

    private static final DocumentContentId contentId = DocumentContentId.builder()
            .build();

    private static final String content = "Test content";

    private static final StayDocument stayDocument = StayDocument.builder()
            .id(contentId)
            .content(content)
            .build();

    @Test
    void 대기문서_객체_생성() {
        StayDocument stayDocument = new StayDocument();
        assertNull(stayDocument.getId());
        assertNull(stayDocument.getContent());
    }

    @Test
    void 대기문서_아이디_가져오기() {
        assertEquals(contentId, stayDocument.getId());
    }

    @Test
    void 대기문서_내용_가져오기() {
        assertEquals(content, stayDocument.getContent());
    }

}