package kr.hs.entrydsm.raisepercent.domain.notification.domain;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {

    private static final String title = "Test title";

    private static final String content = "Test content";

    private static final boolean isWatch = true;

    private static final String icon = "Test icon";

    private static final String type = "Test type";

    private static final String value = "Test value";

    private static final User sender = User.builder()
            .build();

    private static final User receiver = User.builder()
            .build();

    private static final Notification notification = Notification.builder()
            .title(title)
            .content(content)
            .isWatch(isWatch)
            .icon(icon)
            .type(type)
            .value(value)
            .sender(sender)
            .receiver(receiver)
            .build();

    @Test
    void 알림_객체_생성() {
        Notification notification = new Notification();
        assertNull(notification.getUuid());
        assertNull(notification.getTitle());
        assertNull(notification.getContent());
        assertFalse(notification.isWatch());
        assertNull(notification.getIcon());
        assertNull(notification.getType());
        assertNull(notification.getValue());
        assertNull(notification.getSender());
        assertNull(notification.getReceiver());
    }

    @Test
    void 알림_이름_가져오기() {
        assertEquals(title, notification.getTitle());
    }

    @Test
    void 알림_내용_가져오기() {
        assertEquals(content, notification.getContent());
    }

    @Test
    void 알림_읽음여부_가져오기() {
        assertEquals(isWatch, notification.isWatch());
    }

    @Test
    void 알림_아이콘_가져오기() {
        assertEquals(icon, notification.getIcon());
    }

    @Test
    void 알림_종류_가져오기() {
        assertEquals(type, notification.getType());
    }

    @Test
    void 알림_값_가져오기() {
        assertEquals(value, notification.getValue());
    }

    @Test
    void 알림_송신자_가져오기() {
        assertEquals(sender, notification.getSender());
    }

    @Test
    void 알림_수신자_가져오기() {
        assertEquals(receiver, notification.getReceiver());
    }

}