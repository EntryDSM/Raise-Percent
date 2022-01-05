package kr.hs.entrydsm.raisepercent.global.feign.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class OtherBadRequestException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new OtherBadRequestException();

    private OtherBadRequestException() {
        super(ErrorCode.OTHER_BAD_REQUEST);
    }

}
