package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.exception.InvalidTypeException;
import kr.hs.entrydsm.raisepercent.domain.user.facade.UserFacade;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.UpdateDeviceTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateDeviceTokenService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(UpdateDeviceTokenRequest request) {
        User user = userFacade.getCurrentUser();

        switch (request.getType()) {
            case "mobile":
                user.setMobileDeviceToken(request.getDeviceToken());
                break;
            case "web":
                user.setWebDeviceToken(request.getDeviceToken());
                break;
            default:
                throw InvalidTypeException.EXCEPTION;
        }

    }

}
