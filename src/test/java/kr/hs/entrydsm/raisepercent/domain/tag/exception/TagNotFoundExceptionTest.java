package kr.hs.entrydsm.raisepercent.domain.tag.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagNotFoundExceptionTest {

    @Test
    void 존재하지않는_태그() {
        assertThat(ErrorCode.TAG_NOT_FOUND).isEqualTo(TagNotFoundException.EXCEPTION.getErrorCode());
    }

}