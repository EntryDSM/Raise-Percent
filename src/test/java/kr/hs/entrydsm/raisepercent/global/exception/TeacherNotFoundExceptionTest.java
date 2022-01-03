package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeacherNotFoundExceptionTest {

    @Test
    void 선생님_존재하지않음_예외() {
        assertEquals(ErrorCode.TEACHER_NOT_FOUND, TeacherNotFoundException.EXCEPTION.getErrorCode());
    }

}