package kr.hs.entrydsm.raisepercent.infrastructure.feign.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OtherUnAuthorizedExceptionTest {

    @Test
    void 다른서버_인증되지않음_예외() {
        assertEquals(ErrorCode.OTHER_UNAUTHORIZED, OtherUnAuthorizedException.EXCEPTION.getErrorCode());
    }

}
