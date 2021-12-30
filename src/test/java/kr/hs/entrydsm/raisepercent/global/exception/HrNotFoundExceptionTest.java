package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HrNotFoundExceptionTest {

    @Test
    void 인사담당자_존재하지않음_예외() {
        assertEquals(ErrorCode.HR_NOT_FOUND, HrNotFoundException.EXCEPTION.getErrorCode());
    }

}