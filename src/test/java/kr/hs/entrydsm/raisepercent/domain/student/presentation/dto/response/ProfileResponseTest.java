package kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileResponseTest {

    private static final String name = "test";

    private static final String contactEmail = "test@gmail.com";

    private static final String contactTel = "01051039555";

    private static final String position = "Backend";

    private static final List<String> tags = List.of("test", "te");

    private static final ProfileResponse response = new ProfileResponse(name, contactEmail, contactTel, position, tags);

    @Test
    void 이름_가져오기() {
        assertEquals(name, response.getName());
    }

    @Test
    void 이메일_가져오기() {
        assertEquals(contactEmail, response.getContactEmail());
    }

    @Test
    void 전화번호_가져오기() {
        assertEquals(contactTel, response.getContactTel());
    }

    @Test
    void 포지션_가져오기() {
        assertEquals(position, response.getPosition());
    }

    @Test
    void 태그_가져오기() {
        assertEquals(tags, response.getTags());
    }

}