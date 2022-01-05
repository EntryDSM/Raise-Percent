package kr.hs.entrydsm.raisepercent.global.feign.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class OtherExpiredTokenException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new OtherExpiredTokenException();

    private OtherExpiredTokenException() {
        super(ErrorCode.OTHER_EXPIRED_TOKEN);
    }

}
