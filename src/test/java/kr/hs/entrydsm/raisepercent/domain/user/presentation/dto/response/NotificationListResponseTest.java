package kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.response;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationListResponseTest {

    private static final List<NotificationElement> notifications = new ArrayList<>();

    private static final NotificationListResponse response = new NotificationListResponse(notifications);

    @Test
    void 리스트_가져오기() {
        assertEquals(notifications, response.getNotificationList());
    }

}