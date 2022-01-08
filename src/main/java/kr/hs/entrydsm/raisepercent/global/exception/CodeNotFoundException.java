package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class CodeNotFoundException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new CodeNotFoundException();

    private CodeNotFoundException() {
        super(ErrorCode.CODE_NOT_FOUND);
    }

}
