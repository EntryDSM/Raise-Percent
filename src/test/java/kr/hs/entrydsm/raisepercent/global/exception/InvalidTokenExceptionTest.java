package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidTokenExceptionTest {

    @Test
    void 잘못된토큰_예외() {
        assertEquals(ErrorCode.INVALID_TOKEN, InvalidTokenException.EXCEPTION.getErrorCode());
    }

}