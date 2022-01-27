package kr.hs.entrydsm.raisepercent.domain.notice.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class NoticeNotFoundException extends RaiseException {

    public static final NoticeNotFoundException EXCEPTION =
            new NoticeNotFoundException();
}
