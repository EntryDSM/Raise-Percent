package kr.hs.entrydsm.raisepercent.global.security.auth;

import kr.hs.entrydsm.raisepercent.global.security.auth.facade.AuthFacade;
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

        when(authFacade.queryUserRole(email, "teacher"))
                .thenReturn(Type.ROOT);

        for (GrantedAuthority authority : authDetailsService.loadUserByUsername(email, "teacher")
                     .getAuthorities()) {
            assertEquals(Type.ROOT.name(), authority.getAuthority());
        }
    }

}