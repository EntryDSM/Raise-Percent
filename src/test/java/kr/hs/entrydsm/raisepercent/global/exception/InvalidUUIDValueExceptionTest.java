package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidUUIDValueExceptionTest {

    @Test
    void 잘못된_UUID_값() {
        assertEquals(ErrorCode.INVALID_UUID_VALUE, InvalidUUIDValueException.EXCEPTION.getErrorCode());
    }

}