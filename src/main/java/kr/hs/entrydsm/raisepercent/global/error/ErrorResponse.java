package kr.hs.entrydsm.raisepercent.global.error;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int status;
    private final String code;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"status\": " + status +
                ",\t\"code\": \"" + code + '\"' +
                ",\n\t\"message\": \"" + message + '\"' +
                "\n}";
    }
}
