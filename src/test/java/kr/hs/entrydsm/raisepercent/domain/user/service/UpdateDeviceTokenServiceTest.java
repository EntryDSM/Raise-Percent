package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.exception.InvalidTypeException;
import kr.hs.entrydsm.raisepercent.domain.user.facade.UserFacade;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.UpdateDeviceTokenRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UpdateDeviceTokenServiceTest {

    public static final UserFacade userFacade = mock(UserFacade.class);

    public static final UpdateDeviceTokenRequest request = mock(UpdateDeviceTokenRequest.class);

    public static final UpdateDeviceTokenService service = new UpdateDeviceTokenService(userFacade);

    @Test
    void 웹_디바이스토큰_수정() {
        String type = "web";
        String deviceToken = "webtoken";
        User user = User.builder().build();

        when(userFacade.getCurrentUser())
                .thenReturn(user);
        when(request.getType())
                .thenReturn(type);
        when(request.getDeviceToken())
                .thenReturn(deviceToken);

        service.execute(request);

        assertEquals(deviceToken, user.getWebDeviceToken());
    }

    @Test
    void 모바일_디바이스토큰_수정() {
        String type = "mobile";
        String deviceToken = "mobiletoken";
        User user = User.builder().build();

        when(userFacade.getCurrentUser())
                .thenReturn(user);
        when(request.getType())
                .thenReturn(type);
        when(request.getDeviceToken())
                .thenReturn(deviceToken);

        service.execute(request);

        assertEquals(deviceToken, user.getMobileDeviceToken());
    }

    @Test
    void 디바이스토큰_수정_예외() {
        String type = "test";
        String deviceToken = "mobiletoken";
        User user = User.builder().build();

        when(userFacade.getCurrentUser())
                .thenReturn(user);
        when(request.getType())
                .thenReturn(type);
        when(request.getDeviceToken())
                .thenReturn(deviceToken);

        assertThrows(InvalidTypeException.class, () -> service.execute(request));
    }

}