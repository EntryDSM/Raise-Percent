package kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NotificationElementTest {

    private static final String notificationId = "test notification";

    private static final String icon = "test icon";

    private static final String content = "test content";

    private static final LocalDateTime createdAt = LocalDateTime.now();

    private static final boolean isWatch = false;

    private static final String type = "test";

    private static final String value = "test value";

    private static final NotificationElement element = NotificationElement.builder()
            .notificationId(notificationId)
            .icon(icon)
            .content(content)
            .createdAt(createdAt)
            .isWatch(isWatch)
            .type(type)
            .value(value)
            .build();

    @Test
    void 알림_아이디_가져오기() {
        assertEquals(notificationId, element.getNotificationId());
    }

    @Test
    void 알림_아이콘_가져오기() {
        assertEquals(icon, element.getIcon());
    }

    @Test
    void 알림_내용_가져오기() {
        assertEquals(content, element.getContent());
    }

    @Test
    void 알림_생성시간_가져오기() {
        assertEquals(createdAt, element.getCreatedAt());
    }

    @Test
    void 알림_읽음여부_가져오기() {
        assertEquals(isWatch, element.isWatch());
    }

    @Test
    void 알림_타입_가져오기() {
        assertEquals(type, element.getType());
    }

    @Test
    void 알림_값_가져오기() {
        assertEquals(value, element.getValue());
    }

}