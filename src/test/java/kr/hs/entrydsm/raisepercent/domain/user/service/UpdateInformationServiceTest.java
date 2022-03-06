package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.facade.UserFacade;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.UpdateInformationRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UpdateInformationServiceTest {

    @Mock
    private UserFacade userFacade;

    @Mock
    private UpdateInformationRequest request;

    @InjectMocks
    private UpdateInformationService service;

    @Test
    void 유저_정보_갱신() {
        //given
        String contactEmail = "test@gmail.com";
        String contactTel = "01011112222";

        User user = User.builder().build();

        given(userFacade.getCurrentUser())
            .willReturn(user);
        given(request.getContactEmail())
                .willReturn(contactEmail);
        given(request.getContactTel())
                .willReturn(contactTel);

        //when
        service.execute(request);

        //then
        assertThat(contactEmail).isEqualTo(user.getContactEmail());
        assertThat(contactTel).isEqualTo(user.getContactTel());
    }

}