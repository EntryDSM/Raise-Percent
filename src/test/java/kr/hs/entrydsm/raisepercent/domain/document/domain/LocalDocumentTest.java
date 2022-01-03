package kr.hs.entrydsm.raisepercent.domain.document.domain;

import kr.hs.entrydsm.raisepercent.global.entity.DocumentContentId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LocalDocumentTest {

    private static final DocumentContentId contentId = DocumentContentId.builder()
            .build();

    private static final String content = "Test content";

    private static final LocalDocument localDocument = LocalDocument.builder()
            .id(contentId)
            .content(content)
            .build();

    @Test
    void 로컬문서_객체_생성() {
        LocalDocument localDocument = new LocalDocument();
        assertNull(localDocument.getId());
        assertNull(localDocument.getContent());
    }

    @Test
    void 로컬문서_아이디_가져오기() {
        assertEquals(contentId, localDocument.getId());
    }

    @Test
    void 로컬문서_내용_가져오기() {
        assertEquals(content, localDocument.getContent());
    }

}