package kr.hs.entrydsm.raisepercent.global.error;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorResponseTest {

    @Test
    void 예외_반환_생성() {
        //given
        ErrorCode errorCode = ErrorCode.BAD_REQUEST;

        //when
        ErrorResponse errorResponse = new ErrorResponse(errorCode);

        //then
        assertThat(errorCode.getStatus()).isEqualTo(errorResponse.getStatus());
        assertThat(errorCode.getCode()).isEqualTo(errorResponse.getCode());
        assertThat(errorCode.getMessage()).isEqualTo(errorResponse.getMessage());
    }

}