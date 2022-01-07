package kr.hs.entrydsm.raisepercent.domain.user.facade;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.global.exception.CredentialsNotFoundException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserFacadeTest {

    private static final Authentication authentication = mock(Authentication.class);

    private static final AuthDetails authDetails = mock(AuthDetails.class);

    private static final UserRepository userRepository = mock(UserRepository.class);

    private static final AuthFacade authFacade = new AuthFacade();

    private static final UserFacade userFacade = new UserFacade(authFacade, userRepository);

    @BeforeEach
    void securityContextConfig() {
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    @Test
    void 현재_유저_가져오기() {
        String email = "test@gmail.com";

        User user = User.builder()
                .email(email)
                .build();

        when(authDetails.getUsername())
                .thenReturn(email);
        when(userRepository.findById(email))
                .thenReturn(Optional.of(user));
        when(authentication.getPrincipal())
                .thenReturn(authDetails);

        assertEquals(email, userFacade.getCurrentUser().getEmail());
    }

    @Test
    void 인증객체_존재하지않음() {
        String email = "test@gmail.com";

        User user = User.builder()
                .email(email)
                .build();

        when(authDetails.getUsername())
                .thenReturn(email);
        when(userRepository.findById(email))
                .thenReturn(Optional.of(user));
        when(authentication.getPrincipal())
                .thenReturn(null);

        assertThrows(CredentialsNotFoundException.class, userFacade::getCurrentUser);
    }

}