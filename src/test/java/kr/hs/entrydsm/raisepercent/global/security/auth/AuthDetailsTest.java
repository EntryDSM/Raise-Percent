package kr.hs.entrydsm.raisepercent.global.security.auth;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import static org.junit.jupiter.api.Assertions.*;

class AuthDetailsTest {

    private static final String email = "test@gmail.com";

    private static final String role = "Test role";

    private static final AuthDetails authDetails = new AuthDetails(email, role);


    @Test
    void 인증객체_권한_가져오기() {
        for(GrantedAuthority authority : authDetails.getAuthorities()) {
            assertEquals(role, authority.getAuthority());
        }
    }

    @Test
    void 인증객체_비밀번호_가져오기() {
        assertNull(authDetails.getPassword());
    }

    @Test
    void 인증객체_이름_가져오기() {
        assertEquals(email, authDetails.getUsername());
    }

    @Test
    void 인증객체_계정_만료여부_가져오기() {
        assertTrue(authDetails.isAccountNonExpired());
    }

    @Test
    void 인증객체_계정_정지여부_가져오기() {
        assertTrue(authDetails.isAccountNonLocked());
    }

    @Test
    void 인증객체_자격증명_만료여부_가져오기() {
        assertTrue(authDetails.isCredentialsNonExpired());
    }

    @Test
    void 인증객체_활성화여부_가져오기() {
        assertTrue(authDetails.isEnabled());
    }

}
