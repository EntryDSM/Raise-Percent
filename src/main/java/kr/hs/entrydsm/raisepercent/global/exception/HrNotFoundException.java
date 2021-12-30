package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class HrNotFoundException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new HrNotFoundException();

    private HrNotFoundException() {
        super(ErrorCode.HR_NOT_FOUND);
    }

}
