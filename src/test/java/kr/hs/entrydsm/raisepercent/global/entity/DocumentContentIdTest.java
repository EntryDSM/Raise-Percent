package kr.hs.entrydsm.raisepercent.global.entity;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class DocumentContentIdTest {

    private final int page = 0;

    private final Document document = Document.builder()
            .build();

    private final DocumentContentId documentContentId = DocumentContentId.builder()
            .page(page)
            .document(document)
            .build();

    @Test
    void 문서내용_객체_생성() {
        DocumentContentId documentContentId = new DocumentContentId();
        assertThat(documentContentId.getPage()).isZero();
        assertNull(documentContentId.getDocument());
    }

    @Test
    void 문서내용_페이지_가져오기() {
        assertThat(documentContentId.getPage()).isEqualTo(page);
    }

    @Test
    void 문서내용_문서_가져오기() {
        assertThat(documentContentId.getDocument()).isEqualTo(document);
    }

}