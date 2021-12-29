package kr.hs.entrydsm.raisepercent.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RaiseException extends RuntimeException {

    private final ErrorCode errorCode;

}
