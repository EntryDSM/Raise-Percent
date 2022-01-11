package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class InvalidEnumValueException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new InvalidEnumValueException();

    private InvalidEnumValueException() {
        super(ErrorCode.INVALID_ENUM_VALUE);
    }

}
