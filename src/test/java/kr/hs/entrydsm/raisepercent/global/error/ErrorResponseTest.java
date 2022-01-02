package kr.hs.entrydsm.raisepercent.global.error;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorResponseTest {

    @Test
    void 예외_반환_생성() {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(errorCode);
        assertEquals(errorCode.getStatus(), errorResponse.getStatus());
        assertEquals(errorCode.getCode(), errorResponse.getCode());
        assertEquals(errorCode.getMessage(), errorResponse.getMessage());
    }

}