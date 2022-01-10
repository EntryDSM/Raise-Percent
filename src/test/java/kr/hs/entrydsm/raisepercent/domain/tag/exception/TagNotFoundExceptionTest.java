package kr.hs.entrydsm.raisepercent.domain.tag.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagNotFoundExceptionTest {

    @Test
    void 존재하지않는_태그() {
        assertEquals(ErrorCode.TAG_NOT_FOUND, TagNotFoundException.EXCEPTION.getErrorCode());
    }

}