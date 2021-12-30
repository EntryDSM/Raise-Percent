package kr.hs.entrydsm.raisepercent.domain.document.domain;

import kr.hs.entrydsm.raisepercent.global.entity.DocumentContentId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublicDocumentTest {

    private static final DocumentContentId contentId = DocumentContentId.builder()
            .build();

    private static final String content = "Test content";

    private static final PublicDocument publicDocument = PublicDocument.builder()
            .id(contentId)
            .content(content)
            .build();

    @Test
    void 공개문서_객체_생성() {
        PublicDocument publicDocument = new PublicDocument();
        assertNull(publicDocument.getId());
        assertNull(publicDocument.getContent());
    }

    @Test
    void 공개문서_아이디_가져오기() {
        assertEquals(contentId, publicDocument.getId());
    }

    @Test
    void 공개문서_내용_가져오기() {
        assertEquals(content, publicDocument.getContent());
    }

}