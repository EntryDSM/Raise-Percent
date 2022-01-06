package kr.hs.entrydsm.raisepercent.domain.user.service;

import java.util.Optional;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.UpdateInformationRequest;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UpdateInformationServiceTest {

    private static final UserRepository userRepository = mock(UserRepository.class);

    private static final AuthFacade authFacade = mock(AuthFacade.class);

    private static final UpdateInformationRequest request = mock(UpdateInformationRequest.class);

    private static final UpdateInformationService service = new UpdateInformationService(userRepository, authFacade);

    @Test
    void 유저_정보_갱신() {
        String contactEmail = "test@gmail.com";
        String contactTel = "01011112222";

        User user = User.builder().build();

        // when
        when(userRepository.findById(any()))
            .thenReturn(Optional.ofNullable(user));
        when(request.getContactEmail())
                .thenReturn(contactEmail);
        when(request.getContactTel())
                .thenReturn(contactTel);

        service.execute(request);

        assertEquals(contactEmail, user.getContactEmail());
        assertEquals(contactTel, user.getContactTel());
    }

}