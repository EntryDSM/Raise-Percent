package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.facade.UserFacade;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.UpdateInformationRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateInformationServiceTest {

    @Mock
    private static UserFacade userFacade;

    @Mock
    private static UpdateInformationRequest request;

    @InjectMocks
    private static UpdateInformationService service;

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