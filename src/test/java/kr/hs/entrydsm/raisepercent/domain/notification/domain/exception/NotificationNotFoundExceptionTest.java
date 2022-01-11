package kr.hs.entrydsm.raisepercent.domain.notification.domain.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationNotFoundExceptionTest {

    @Test
    void 알림_찾을수없음() {
        assertEquals(ErrorCode.NOTIFICATION_NOT_FOUND, NotificationNotFoundException.EXCEPTION.getErrorCode());
    }

}