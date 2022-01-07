package kr.hs.entrydsm.raisepercent.domain.code.facade;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class CodeAlreadyExistsException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new CodeAlreadyExistsException();

    private CodeAlreadyExistsException() {
        super(ErrorCode.CODE_ALREADY_EXISTS);
    }

}
