package kr.hs.entrydsm.raisepercent.global.security.auth;

import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.jwt.type.TokenRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AuthDetailsServiceTest {

    @Mock
    private AuthFacade authFacade;

    @InjectMocks
    private AuthDetailsService authDetailsService;

    @Test
    void 유저_인증객체_가져오기() {
        //given
        String email = "test@gmail.com";
        TokenRole role = TokenRole.TEACHER;

        given(authFacade.queryUserRole(email, role))
                .willReturn(Type.ROOT);

        //when then
        for (GrantedAuthority authority : authDetailsService.loadUserByUsername(email, role)
                     .getAuthorities()) {
            assertThat(Type.ROOT.name()).isEqualTo(authority.getAuthority());
        }
    }

}