package kr.hs.entrydsm.raisepercent.domain.user.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private static final String email = "test@gamil.com";

    private static final String name = "name";

    private static final User user = User.builder()
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
        assertEquals(name, user.getName());
    }

    @Test
    void 유저_이메일_가져오기() {
        assertEquals(email, user.getEmail());
    }

    @Test
    void 유저_접근_이메일_수정() {
        String contactEmail = "contact@gmail.com";
        user.setContactEmail(contactEmail);
        assertEquals(contactEmail, user.getContactEmail());
    }

    @Test
    void 유저_접근_번호_수정() {
        String contactTel = "01000000000";
        user.setContactTel(contactTel);
        assertEquals(contactTel, user.getContactTel());
    }

    @Test
    void 유저_모바일_토큰_수정() {
        String mobileDeviceToken = "Mobile Device Token";
        user.setMobileDeviceToken(mobileDeviceToken);
        assertEquals(mobileDeviceToken, user.getMobileDeviceToken());
    }

    @Test
    void 유저_웹_토큰_수정() {
        String webDeviceToken = "Web Device Token";
        user.setWebDeviceToken(webDeviceToken);
        assertEquals(webDeviceToken, user.getWebDeviceToken());
    }

    @Test
    void 유저_권한_변환() {
        assertNull(user.queryType());
    }

}