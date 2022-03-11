package kr.hs.entrydsm.raisepercent.domain.user.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class TokenNotFoundException extends RaiseException {

    public static final RaiseException EXCEPTION = new TokenNotFoundException();

    private TokenNotFoundException() {
        super(ErrorCode.TOKEN_NOT_FOUND);
    }
}
