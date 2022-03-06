package kr.hs.entrydsm.raisepercent.domain.tag.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AlreadyRegisteredTagExceptionTest {

    @Test
    void 이미_등록된_태그() {
        assertThat(ErrorCode.ALREADY_REGISTERED_TAG).isEqualTo(AlreadyRegisteredTagException.EXCEPTION.getErrorCode());
    }

}