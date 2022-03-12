package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.exception.InvalidTypeException;
import kr.hs.entrydsm.raisepercent.domain.user.facade.UserFacade;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.UpdateDeviceTokenRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UpdateDeviceTokenServiceTest {

    @Mock
    private UserFacade userFacade;

    @InjectMocks
    private UpdateDeviceTokenService service;

    @Mock
    private UpdateDeviceTokenRequest request;

    @Test
    void 웹_디바이스토큰_수정() {
        //given
        String type = "web";
        String deviceToken = "webtoken";
        User user = User.builder().build();

        given(userFacade.getCurrentUser())
                .willReturn(user);
        given(request.getType())
                .willReturn(type);
        given(request.getDeviceToken())
                .willReturn(deviceToken);

        //when
        service.execute(request);

        //then
        assertThat(deviceToken).isEqualTo(user.getWebDeviceToken());
    }

    @Test
    void 모바일_디바이스토큰_수정() {
        //given
        String type = "mobile";
        String deviceToken = "mobiletoken";
        User user = User.builder().build();

        given(userFacade.getCurrentUser())
                .willReturn(user);
        given(request.getType())
                .willReturn(type);
        given(request.getDeviceToken())
                .willReturn(deviceToken);

        //when
        service.execute(request);

        //then
        assertThat(deviceToken).isEqualTo(user.getMobileDeviceToken());
    }

    @Test
    void 디바이스토큰_수정_예외() {
        //given
        String type = "test";
        User user = User.builder().build();

        given(userFacade.getCurrentUser())
                .willReturn(user);
        given(request.getType())
                .willReturn(type).getMock();

        //when then
        assertThrows(InvalidTypeException.class, () -> service.execute(request));
    }

}