package kr.hs.entrydsm.raisepercent.infrastructure.feign.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class OtherUnAuthorizedException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new OtherUnAuthorizedException();

    private OtherUnAuthorizedException() {
        super(ErrorCode.OTHER_UNAUTHORIZED);
    }

}
