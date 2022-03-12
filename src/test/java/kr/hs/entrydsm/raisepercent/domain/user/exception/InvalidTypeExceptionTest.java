package kr.hs.entrydsm.raisepercent.domain.user.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InvalidTypeExceptionTest {

    @Test
    void 잘못된_타입_예외() {
        assertThat(ErrorCode.INVALID_TYPE).isEqualTo(InvalidTypeException.EXCEPTION.getErrorCode());
    }

}