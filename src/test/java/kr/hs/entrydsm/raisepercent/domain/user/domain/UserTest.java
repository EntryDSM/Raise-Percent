package kr.hs.entrydsm.raisepercent.domain.user.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserTest {

    private final String email = "test@gamil.com";

    private final String name = "name";

    private final User user = User.builder()
            .email(email)
            .name(name)
            .build();

    @Test
    void 유저_객체_생성() {
        User user = new User();
        assertNull(user.getEmail());
        assertNull(user.getName());
        assertNull(user.getContactEmail());
        assertNull(user.getContactTel());
        assertNull(user.getMobileDeviceToken());
        assertNull(user.getWebDeviceToken());
    }

    @Test
    void 유저_이름_가져오기() {
        assertThat(name).isEqualTo(user.getName());
    }

    @Test
    void 유저_이메일_가져오기() {
        assertThat(email).isEqualTo(user.getEmail());
    }

    @Test
    void 유저_접근_이메일_수정() {
        String contactEmail = "contact@gmail.com";
        user.setContactEmail(contactEmail);
        assertThat(contactEmail).isEqualTo(user.getContactEmail());
    }

    @Test
    void 유저_접근_번호_수정() {
        String contactTel = "01000000000";
        user.setContactTel(contactTel);
        assertThat(contactTel).isEqualTo(user.getContactTel());
    }

    @Test
    void 유저_모바일_토큰_수정() {
        String mobileDeviceToken = "Mobile Device Token";
        user.setMobileDeviceToken(mobileDeviceToken);
        assertThat(mobileDeviceToken).isEqualTo(user.getMobileDeviceToken());
    }

    @Test
    void 유저_웹_토큰_수정() {
        String webDeviceToken = "Web Device Token";
        user.setWebDeviceToken(webDeviceToken);
        assertThat(webDeviceToken).isEqualTo(user.getWebDeviceToken());
    }

    @Test
    void 유저_권한_변환() {
        assertNull(user.queryType());
    }

}