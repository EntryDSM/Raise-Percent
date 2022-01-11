package kr.hs.entrydsm.raisepercent.domain.notification.domain.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class NotificationNotFoundException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new NotificationNotFoundException();

    private NotificationNotFoundException() {
        super(ErrorCode.NOTIFICATION_NOT_FOUND);
    }

}
