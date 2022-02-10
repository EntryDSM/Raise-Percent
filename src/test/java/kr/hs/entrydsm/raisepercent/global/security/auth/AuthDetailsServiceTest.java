package kr.hs.entrydsm.raisepercent.global.security.auth;

import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.jwt.type.TokenRole;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthDetailsServiceTest {

    private AuthFacade authFacade = mock(AuthFacade.class);

    private AuthDetailsService authDetailsService = new AuthDetailsService(authFacade);

    @Test
    void 유저_인증객체_가져오기() {
        String email = "test@gmail.com";
        TokenRole role = TokenRole.TEACHER;

        when(authFacade.queryUserRole(email, role))
                .thenReturn(Type.ROOT);

        for (GrantedAuthority authority : authDetailsService.loadUserByUsername(email, role)
                     .getAuthorities()) {
            assertEquals(Type.ROOT.name(), authority.getAuthority());
        }
    }

}