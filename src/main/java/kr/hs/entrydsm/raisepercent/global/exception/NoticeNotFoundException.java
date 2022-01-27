package kr.hs.entrydsm.raisepercent.global.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class NoticeNotFoundException extends RaiseException {

    public static final NoticeNotFoundException EXCEPTION =
            new NoticeNotFoundException();

    private NoticeNotFoundException() {
        super(ErrorCode.NOTICE_NOT_FOUND);
    }
}
