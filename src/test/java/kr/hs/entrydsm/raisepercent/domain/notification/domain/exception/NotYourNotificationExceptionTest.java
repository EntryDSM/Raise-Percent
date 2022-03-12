package kr.hs.entrydsm.raisepercent.domain.notification.domain.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NotYourNotificationExceptionTest {

    @Test
    void 자신의_알림이_아님() {
        assertThat(ErrorCode.NOT_YOUR_NOTIFICATION).isEqualTo(NotYourNotificationException.EXCEPTION.getErrorCode());
    }

}