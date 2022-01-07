package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentNotFoundExceptionTest {

    @Test
    public void 문서_존재_예외() {
        assertEquals(ErrorCode.DOCUMENT_NOT_FOUND, DocumentNotFoundException.EXCEPTION.getErrorCode());
    }
}
