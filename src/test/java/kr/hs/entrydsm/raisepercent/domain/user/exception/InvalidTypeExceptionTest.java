package kr.hs.entrydsm.raisepercent.domain.user.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidTypeExceptionTest {

    @Test
    void 잘못된_타입_예외() {
        assertEquals(ErrorCode.INVALID_TYPE, InvalidTypeException.EXCEPTION.getErrorCode());
    }

}