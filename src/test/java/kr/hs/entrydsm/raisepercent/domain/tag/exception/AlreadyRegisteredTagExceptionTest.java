package kr.hs.entrydsm.raisepercent.domain.tag.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlreadyRegisteredTagExceptionTest {

    @Test
    void 이미_등록된_태그() {
        assertEquals(ErrorCode.ALREADY_REGISTERED_TAG, AlreadyRegisteredTagException.EXCEPTION.getErrorCode());
    }

}