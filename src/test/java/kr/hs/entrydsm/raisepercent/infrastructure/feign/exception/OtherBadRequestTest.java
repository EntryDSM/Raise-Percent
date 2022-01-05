package kr.hs.entrydsm.raisepercent.infrastructure.feign.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OtherBadRequestTest {

    @Test
    void 다른서버_잘못된요청_예외() {
        assertEquals(ErrorCode.OTHER_BAD_REQUEST, OtherBadRequestException.EXCEPTION.getErrorCode());
    }

}
