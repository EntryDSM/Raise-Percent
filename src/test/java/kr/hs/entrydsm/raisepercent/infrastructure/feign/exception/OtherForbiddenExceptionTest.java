package kr.hs.entrydsm.raisepercent.infrastructure.feign.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OtherForbiddenExceptionTest {

    @Test
    void 다른서버_권한없음_예외() {
        assertEquals(ErrorCode.OTHER_FORBIDDEN, OtherForbiddenException.EXCEPTION.getErrorCode());
    }

}
