package kr.hs.entrydsm.raisepercent.domain.user.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidTokenException;

public class InvalidTypeException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new InvalidTypeException();

    private InvalidTypeException() {
        super(ErrorCode.INVALID_TYPE);
    }

}
