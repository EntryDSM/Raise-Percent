package kr.hs.entrydsm.raisepercent.domain.bookmark.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class AlreadyRegisteredBookmark extends RaiseException {

    public static final RaiseException  EXCEPTION =
            new AlreadyRegisteredBookmark();

    private AlreadyRegisteredBookmark() {
        super(ErrorCode.ALREADY_REGISTERED_BOOKMARK);
    }

}
