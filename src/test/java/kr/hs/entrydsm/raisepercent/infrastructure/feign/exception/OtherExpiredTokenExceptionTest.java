package kr.hs.entrydsm.raisepercent.infrastructure.feign.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OtherExpiredTokenExceptionTest {

    @Test
    void 다른서버_만료된토큰_예외() {
        assertEquals(ErrorCode.OTHER_EXPIRED_TOKEN, OtherExpiredTokenException.EXCEPTION.getErrorCode());
    }

}
