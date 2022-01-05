package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CredentialsNotFoundExceptionTest {

    @Test
    void 인증객체_존재하지않음_예외() {
        assertEquals(ErrorCode.CREDENTIALS_NOT_FOUND, CredentialsNotFoundException.EXCEPTION.getErrorCode());
    }

}