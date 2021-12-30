package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentNotFoundExceptionTest {

    @Test
    void 학생_존재하지않음_예외() {
        assertEquals(ErrorCode.STUDENT_NOT_FOUND, StudentNotFoundException.EXCEPTION.getErrorCode());
    }

}