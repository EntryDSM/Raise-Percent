package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidRoleExceptionTest {

    @Test
    public void 권한_거부_예외() {
        assertEquals(ErrorCode.INVALID_ROLE, InvalidRoleException.EXCEPTION.getErrorCode());
    }
}
