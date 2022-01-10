package kr.hs.entrydsm.raisepercent.domain.code.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodeNotFoundExceptionTest {

    @Test
    void 코드_존재하지않음_예외() {
        assertEquals(ErrorCode.CODE_NOT_FOUND, CodeNotFoundException.EXCEPTION.getErrorCode());
    }

}
