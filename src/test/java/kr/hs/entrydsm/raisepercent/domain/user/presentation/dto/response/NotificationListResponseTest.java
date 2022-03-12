package kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.response;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationListResponseTest {

    private final List<NotificationElement> notifications = new ArrayList<>();

    private final NotificationListResponse response = new NotificationListResponse(notifications);

    @Test
    void 리스트_가져오기() {
        assertThat(notifications).isEqualTo(response.getNotificationList());
    }

}