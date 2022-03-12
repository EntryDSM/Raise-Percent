package kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NotificationElementTest {

    private final String notificationId = "test notification";

    private final String icon = "test icon";

    private final String content = "test content";

    private final LocalDateTime createdAt = LocalDateTime.now();

    private final boolean isWatch = false;

    private final String type = "test";

    private final String value = "test value";

    private final NotificationElement element = NotificationElement.builder()
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
        assertThat(notificationId).isEqualTo(element.getNotificationId());
    }

    @Test
    void 알림_아이콘_가져오기() {
        assertThat(icon).isEqualTo(element.getIcon());
    }

    @Test
    void 알림_내용_가져오기() {
        assertThat(content).isEqualTo(element.getContent());
    }

    @Test
    void 알림_생성시간_가져오기() {
        assertThat(createdAt).isEqualTo(element.getCreatedAt());
    }

    @Test
    void 알림_읽음여부_가져오기() {
        assertThat(isWatch).isEqualTo(element.isWatch());
    }

    @Test
    void 알림_타입_가져오기() {
        assertThat(type).isEqualTo(element.getType());
    }

    @Test
    void 알림_값_가져오기() {
        assertThat(value).isEqualTo(element.getValue());
    }

}