package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class StudentNotFoundException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new StudentNotFoundException();

    private StudentNotFoundException() {
        super(ErrorCode.STUDENT_NOT_FOUND);
    }

}
