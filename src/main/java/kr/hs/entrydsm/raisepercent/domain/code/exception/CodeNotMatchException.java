package kr.hs.entrydsm.raisepercent.domain.code.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class CodeNotMatchException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new CodeNotMatchException();

    private CodeNotMatchException() {
        super(ErrorCode.CODE_NOT_MATCH);
    }

}
