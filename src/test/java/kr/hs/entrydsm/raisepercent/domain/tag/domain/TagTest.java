package kr.hs.entrydsm.raisepercent.domain.tag.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagTest {

    private static final String name = "Test tag";

    private static final Tag tag = Tag.builder()
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
        assertEquals(name, tag.getName());
    }


}