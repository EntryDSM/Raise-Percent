package kr.hs.entrydsm.raisepercent.global.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class BaseDocumentContentEntityTest {

    private final DocumentContentId documentContentid = DocumentContentId.builder()
            .build();

    private final String content = "Test content";

    private final BaseDocumentContentEntity baseDocumentContentEntity = BaseDocumentContentEntity.builder()
            .id(documentContentid)
            .content(content)
            .build();

    @Test
    void 기본문서내용_객체_생성() {
        BaseDocumentContentEntity baseDocumentContentEntity = new BaseDocumentContentEntity();
        assertNull(baseDocumentContentEntity.getId());
        assertNull(baseDocumentContentEntity.getContent());
    }

    @Test
    void 기본문서내용_아이디_가져오기() {
        assertThat(documentContentid).isEqualTo(baseDocumentContentEntity.getId());
    }

    @Test
    void 기본문서내용_내용_가져오기() {
        assertThat(content).isEqualTo(baseDocumentContentEntity.getContent());
    }

}