package kr.hs.entrydsm.raisepercent.domain.document.domain;

import kr.hs.entrydsm.raisepercent.global.entity.DocumentContentId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PublicDocumentTest {

    private final DocumentContentId contentId = DocumentContentId.builder()
            .build();

    private final String content = "Test content";

    private final PublicDocument publicDocument = PublicDocument.builder()
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
        assertThat(contentId).isEqualTo(publicDocument.getId());
    }

    @Test
    void 공개문서_내용_가져오기() {
        assertThat(content).isEqualTo(publicDocument.getContent());
    }

}