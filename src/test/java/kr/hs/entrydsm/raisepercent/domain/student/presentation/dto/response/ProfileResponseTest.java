package kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileResponseTest {

    private final String name = "test";

    private final String contactEmail = "test@gmail.com";

    private final String contactTel = "01051039555";

    private final String position = "Backend";

    private final List<String> tags = List.of("test", "te");

    private final ProfileResponse response = new ProfileResponse(name, contactEmail, contactTel, position, tags);

    @Test
    void 이름_가져오기() {
        assertThat(name).isEqualTo(response.getName());
    }

    @Test
    void 이메일_가져오기() {
        assertThat(contactEmail).isEqualTo(response.getContactEmail());
    }

    @Test
    void 전화번호_가져오기() {
        assertThat(contactTel).isEqualTo(response.getContactTel());
    }

    @Test
    void 포지션_가져오기() {
        assertThat(position).isEqualTo(response.getPosition());
    }

    @Test
    void 태그_가져오기() {
        assertThat(tags).isEqualTo(response.getTags());
    }

}