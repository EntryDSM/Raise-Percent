package kr.hs.entrydsm.raisepercent.domain.teacher.code.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodeNotMatchExceptionTest {

    @Test
    void 코드_일치하지않음_예외() {
        assertEquals(ErrorCode.CODE_NOT_MATCH, CodeNotMatchException.EXCEPTION.getErrorCode());
    }

}
