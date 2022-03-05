package kr.hs.entrydsm.raisepercent.global.security.auth;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthDetailsTest {

    private final String email = "test@gmail.com";

    private final Type role = Type.ROOT;

    private final AuthDetails authDetails = new AuthDetails(email, role);


    @Test
    void 인증객체_권한_가져오기() {
        for(GrantedAuthority authority : authDetails.getAuthorities()) {
            assertThat(role.name()).isEqualTo(authority.getAuthority());
        }
    }

    @Test
    void 인증객체_비밀번호_가져오기() {
        assertNull(authDetails.getPassword());
    }

    @Test
    void 인증객체_이름_가져오기() {
        assertThat(email).isEqualTo(authDetails.getUsername());
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
