package kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.response;

import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response.UserAuthLinkResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserAuthLinkResponseTest {

    private static final String link = "EntryDSM";

    private static final UserAuthLinkResponse response = UserAuthLinkResponse.builder()
            .link(link)
            .build();

    @Test
    void 링크_가져오기() {
        assertThat(response.getLink()).isEqualTo(link);
    }

}