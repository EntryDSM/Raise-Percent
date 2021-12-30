package kr.hs.entrydsm.raisepercent.global.entity;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentContentIdTest {

    private static final int page = 0;

    private static final Document document = Document.builder()
            .build();

    private static final DocumentContentId documentContentId = DocumentContentId.builder()
            .page(page)
            .document(document)
            .build();

    @Test
    void 문서내용_객체_생성() {
        DocumentContentId documentContentId = new DocumentContentId();
        assertEquals(0, documentContentId.getPage());
        assertNull(documentContentId.getDocument());
    }

    @Test
    void 문서내용_페이지_가져오기() {
        assertEquals(page, documentContentId.getPage());
    }

    @Test
    void 문서내용_문서_가져오기() {
        assertEquals(document, documentContentId.getDocument());
    }

}