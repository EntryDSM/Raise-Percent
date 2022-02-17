package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class CompanyNotFoundException extends RaiseException {
    public static final RaiseException EXCEPTION =
            new CompanyNotFoundException();

    private CompanyNotFoundException() {
        super(ErrorCode.COMPANY_NOT_FOUND);
    }
}
