package kr.hs.entrydsm.raisepercent.domain.code.exception;

import kr.hs.entrydsm.raisepercent.domain.code.facade.CodeAlreadyExistsException;
import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodeAlreadyExistsExceptionTest {

    @Test
    void 코드_중복_예외() {
        assertEquals(ErrorCode.CODE_ALREADY_EXISTS, CodeAlreadyExistsException.EXCEPTION.getErrorCode());
    }

}
