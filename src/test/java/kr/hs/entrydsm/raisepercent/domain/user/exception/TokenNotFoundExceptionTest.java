package kr.hs.entrydsm.raisepercent.domain.user.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenNotFoundExceptionTest {

    @Test
    void 잘못된_토큰_예외() {
        assertEquals(ErrorCode.TOKEN_NOT_FOUND, TokenNotFoundException.EXCEPTION.getErrorCode());
    }
}
