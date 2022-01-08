package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedbackNotFoundExceptionTest {

    @Test
    void 피드백_존재_예외() {
        assertEquals(ErrorCode.FEEDBACK_NOT_FOUND, FeedbackNotFoundException.EXCEPTION.getErrorCode());
    }
}
