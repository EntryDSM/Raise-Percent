package kr.hs.entrydsm.raisepercent.domain.bookmark.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class AlreadyRegisteredBookmarkException extends RaiseException {

    public static final RaiseException  EXCEPTION =
            new AlreadyRegisteredBookmarkException();

    private AlreadyRegisteredBookmarkException() {
        super(ErrorCode.ALREADY_REGISTERED_BOOKMARK);
    }

}
