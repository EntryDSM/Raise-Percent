package kr.hs.entrydsm.raisepercent.domain.tag.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class TagTest {

    private final String name = "Test tag";

    private final Tag tag = Tag.builder()
            .name(name)
            .build();

    @Test
    void 태그_객체_생성() {
        Tag tag = new Tag();
        assertNull(tag.getId());
        assertNull(tag.getName());
    }

    @Test
    void 태그_이름_가져오기() {
        assertThat(name).isEqualTo(tag.getName());
    }


}