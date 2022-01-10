package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class FeedbackNotFoundException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new FeedbackNotFoundException();

    private FeedbackNotFoundException() {
        super(ErrorCode.FEEDBACK_NOT_FOUND);
    }
}
