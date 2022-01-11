package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidEnumValueExceptionTest {

    @Test
    void 잘못된_enum_값() {
        assertEquals(ErrorCode.INVALID_ENUM_VALUE, InvalidEnumValueException.EXCEPTION.getErrorCode());
    }

}