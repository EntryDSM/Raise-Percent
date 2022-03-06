package kr.hs.entrydsm.raisepercent.domain.teacher.code.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CodeNotMatchExceptionTest {

    @Test
    void 코드_일치하지않음_예외() {
        assertThat(ErrorCode.CODE_NOT_MATCH).isEqualTo(CodeNotMatchException.EXCEPTION.getErrorCode());
    }

}
