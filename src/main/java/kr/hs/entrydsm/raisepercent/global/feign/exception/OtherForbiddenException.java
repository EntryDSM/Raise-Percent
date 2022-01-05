package kr.hs.entrydsm.raisepercent.global.feign.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class OtherForbiddenException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new OtherForbiddenException();

    private OtherForbiddenException() {
        super(ErrorCode.OTHER_FORBIDDEN);
    }

}
