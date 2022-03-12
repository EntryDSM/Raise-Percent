package kr.hs.entrydsm.raisepercent.domain.document.domain;

import kr.hs.entrydsm.raisepercent.global.entity.DocumentContentId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LocalDocumentTest {

    private final DocumentContentId contentId = DocumentContentId.builder()
            .build();

    private final String content = "Test content";

    private final LocalDocument localDocument = LocalDocument.builder()
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
        assertThat(contentId).isEqualTo(localDocument.getId());
    }

    @Test
    void 로컬문서_내용_가져오기() {
        assertThat(content).isEqualTo(localDocument.getContent());
    }

}