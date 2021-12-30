package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNotFoundExceptionTest {

    @Test
    void 유저_존재하지않음_예외() {
        assertEquals(ErrorCode.USER_NOT_FOUND, UserNotFoundException.EXCEPTION.getErrorCode());
    }

}