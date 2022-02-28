package kr.hs.entrydsm.raisepercent.domain.user.presentation;

import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.UpdateDeviceTokenRequest;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.UpdateInformationRequest;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.response.NotificationListResponse;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.response.UserAuthLinkResponse;
import kr.hs.entrydsm.raisepercent.domain.user.service.CheckNotificationService;
import kr.hs.entrydsm.raisepercent.domain.user.service.QueryNotificationListService;
import kr.hs.entrydsm.raisepercent.domain.user.service.QueryUserAuthLinkService;
import kr.hs.entrydsm.raisepercent.domain.user.service.UpdateDeviceTokenService;
import kr.hs.entrydsm.raisepercent.domain.user.service.UpdateInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UpdateInformationService updateInformationService;
    private final UpdateDeviceTokenService updateDeviceTokenService;
    private final QueryNotificationListService queryNotificationListService;
    private final CheckNotificationService checkNotificationService;
    private final QueryUserAuthLinkService queryUserAuthLinkService;

    @PatchMapping("/information")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInformation(@RequestBody @Valid UpdateInformationRequest request) {
        updateInformationService.execute(request);
    }

    @PatchMapping("/device-token")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDeviceToken(@RequestBody @Valid UpdateDeviceTokenRequest request) {
        updateDeviceTokenService.execute(request);
    }

    @GetMapping("/notifications")
    public NotificationListResponse queryNotificationList(Pageable pageable) {
        return queryNotificationListService.execute(pageable);
    }

    @PatchMapping("/notifications/{notification-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void checkNotification(@PathVariable("notification-id") String notificationId) {
        checkNotificationService.execute(notificationId);
    }

    @GetMapping("/auth")
    public UserAuthLinkResponse queryUserAuthLink() {
        return queryUserAuthLinkService.execute();
    }

}
