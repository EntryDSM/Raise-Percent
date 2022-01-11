package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.notification.domain.facade.NotificationFacade;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.facade.UserFacade;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QueryNotificationListServiceTest {

    private static final UserFacade userFacade = mock(UserFacade.class);

    private static final NotificationFacade notificationFacade = mock(NotificationFacade.class);

    private static final QueryNotificationListService service = new QueryNotificationListService(userFacade, notificationFacade);

    @Test
    void 알림_리스트_가져오기() {
        User user = User.builder().build();
        Pageable pageable = PageRequest.of(5, 5);

        when(userFacade.getCurrentUser())
                .thenReturn(user);
        when(notificationFacade.queryNotificationElement(user, pageable))
                .thenReturn(new ArrayList<>());

        service.execute(pageable);
    }


}