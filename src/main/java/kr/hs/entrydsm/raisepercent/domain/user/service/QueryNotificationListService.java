package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.notification.domain.facade.NotificationFacade;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.facade.UserFacade;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.response.NotificationListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryNotificationListService {

    private final UserFacade userFacade;
    private final NotificationFacade notificationFacade;

    public NotificationListResponse execute(Pageable pageable) {
        User receiver = userFacade.getCurrentUser();
        return new NotificationListResponse(notificationFacade.queryNotificationElement(receiver, pageable));
    }

}
