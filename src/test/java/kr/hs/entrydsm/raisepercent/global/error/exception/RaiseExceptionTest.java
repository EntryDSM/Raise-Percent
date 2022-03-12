package kr.hs.entrydsm.raisepercent.global.error.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RaiseExceptionTest {

    @Test
    void 예외_생성() {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST;
        RaiseException raiseException = new RaiseException(errorCode);
        assertThat(errorCode).isEqualTo(raiseException.getErrorCode());
    }

}