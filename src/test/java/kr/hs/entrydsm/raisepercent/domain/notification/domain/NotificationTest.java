package kr.hs.entrydsm.raisepercent.domain.notification.domain;

import kr.hs.entrydsm.raisepercent.domain.notification.domain.types.Type;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NotificationTest {

    private final String title = "Test title";

    private final String content = "Test content";

    private final boolean isWatch = false;

    private final String icon = "Test icon";

    private final Type type = Type.DOCUMENT;

    private final String value = "Test value";

    private final User sender = User.builder()
            .build();

    private final User receiver = User.builder()
            .build();

    private final Notification notification = Notification.builder()
            .title(title)
            .content(content)
            .isWatch(isWatch)
            .icon(icon)
            .documentType(type)
            .value(value)
            .sender(sender)
            .receiver(receiver)
            .build();

    @Test
    void 알림_객체_생성() {
        Notification notification = new Notification();
        assertNull(notification.getId());
        assertNull(notification.getTitle());
        assertNull(notification.getContent());
        assertFalse(notification.isWatch());
        assertNull(notification.getIcon());
        assertNull(notification.getDocumentType());
        assertNull(notification.getValue());
        assertNull(notification.getSender());
        assertNull(notification.getReceiver());
        assertNull(notification.getCreatedAt());
    }

    @Test
    void 알림_이름_가져오기() {
        assertThat(title).isEqualTo(notification.getTitle());
    }

    @Test
    void 알림_내용_가져오기() {
        assertThat(content).isEqualTo(notification.getContent());
    }

    @Order(0)
    @Test
    void 알림_읽음여부_가져오기() {
        assertThat(isWatch).isEqualTo(notification.isWatch());
    }

    @Test
    void 알림_아이콘_가져오기() {
        assertThat(icon).isEqualTo(notification.getIcon());
    }

    @Test
    void 알림_종류_가져오기() {
        assertThat(type).isEqualTo(notification.getDocumentType());
    }

    @Test
    void 알림_값_가져오기() {
        assertThat(value).isEqualTo(notification.getValue());
    }

    @Test
    void 알림_송신자_가져오기() {
        assertThat(sender).isEqualTo(notification.getSender());
    }

    @Test
    void 알림_수신자_가져오기() {
        assertThat(receiver).isEqualTo(notification.getReceiver());
    }

    @Order(1)
    @Test
    void 알림_확인() {
        notification.checkNotification();
        assertTrue(notification.isWatch());
    }

}