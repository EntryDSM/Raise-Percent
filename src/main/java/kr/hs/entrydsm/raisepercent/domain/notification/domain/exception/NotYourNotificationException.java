package kr.hs.entrydsm.raisepercent.domain.notification.domain.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class NotYourNotificationException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new NotYourNotificationException();

    private NotYourNotificationException() {
        super(ErrorCode.NOT_YOUR_NOTIFICATION);
    }

}
