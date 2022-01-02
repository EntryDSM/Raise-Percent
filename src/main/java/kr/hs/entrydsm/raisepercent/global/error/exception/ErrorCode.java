package kr.hs.entrydsm.raisepercent.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    BAD_REQUEST(400, "GLOBAL-400-1", "Bad request."),

    EXPIRED_TOKEN(401, "GLOBAL-401-1", "Expired token."),
    INVALID_TOKEN(401, "GLOBAL-401-2", "Invalid token."),

    USER_NOT_FOUND(404, "GLOBAL-404-1", "User not found."),
    STUDENT_NOT_FOUND(404, "GLOBAL-404-2", "Student not found."),
    TEACHER_NOT_FOUND(404, "GLOBAL-404-3", "Teacher not found."),
    HR_NOT_FOUND(404, "GLOBAL-404-4", "Hr not found.");

    private final int status;
    private final String code;
    private final String message;

}
