package kr.hs.entrydsm.raisepercent.domain.tag.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import kr.hs.entrydsm.raisepercent.global.error.exception.RaiseException;

public class TagNotFoundException extends RaiseException {

    public static final RaiseException EXCEPTION =
            new TagNotFoundException();

    private TagNotFoundException() {
        super(ErrorCode.TAG_NOT_FOUND);
    }

}
