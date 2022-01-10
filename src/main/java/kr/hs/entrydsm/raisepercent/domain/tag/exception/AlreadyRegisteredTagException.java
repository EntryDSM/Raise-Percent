package kr.hs.entrydsm.raisepercent.domain.tag.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class AlreadyRegisteredTagException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new AlreadyRegisteredTagException();

    private AlreadyRegisteredTagException() {
        super(ErrorCode.ALREADY_REGISTERED_TAG);
    }

}
