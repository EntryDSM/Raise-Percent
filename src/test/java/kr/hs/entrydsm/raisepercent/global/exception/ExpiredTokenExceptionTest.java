package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpiredTokenExceptionTest {

    @Test
    void 만료된토큰_예외() {
        assertEquals(ErrorCode.EXPIRED_TOKEN, ExpiredTokenException.EXCEPTION.getErrorCode());
    }

}