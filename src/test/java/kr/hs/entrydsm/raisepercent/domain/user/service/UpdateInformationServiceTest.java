package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.facade.UserFacade;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.UpdateInformationRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UpdateInformationServiceTest {

    private static final UserFacade userFacade = mock(UserFacade.class);

    private static final UpdateInformationRequest request = mock(UpdateInformationRequest.class);

    private static final UpdateInformationService service = new UpdateInformationService(userFacade);

    @Test
    void 유저_정보_갱신() {
        String contactEmail = "test@gmail.com";
        String contactTel = "01011112222";

        User user = User.builder().build();

        when(userFacade.getCurrentUser())
            .thenReturn(user);
        when(request.getContactEmail())
                .thenReturn(contactEmail);
        when(request.getContactTel())
                .thenReturn(contactTel);

        service.execute(request);

        assertEquals(contactEmail, user.getContactEmail());
        assertEquals(contactTel, user.getContactTel());
    }

}