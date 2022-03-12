package kr.hs.entrydsm.raisepercent.domain.teacher.code.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CodeNotFoundExceptionTest {

    @Test
    void 코드_존재하지않음_예외() {
        assertThat(ErrorCode.CODE_NOT_FOUND).isEqualTo(CodeNotFoundException.EXCEPTION.getErrorCode());
    }

}
