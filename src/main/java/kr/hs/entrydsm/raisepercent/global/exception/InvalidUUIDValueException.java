package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class InvalidUUIDValueException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new InvalidUUIDValueException();

    private InvalidUUIDValueException() {
        super(ErrorCode.INVALID_UUID_VALUE);
    }

}
