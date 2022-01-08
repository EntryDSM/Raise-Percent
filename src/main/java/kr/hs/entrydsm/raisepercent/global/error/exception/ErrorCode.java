package kr.hs.entrydsm.raisepercent.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    CODE_ALREADY_EXISTS(409, "CODE-409-1", "Code Already Exists"),

    BAD_REQUEST(400, "GLOBAL-400-1", "Bad request."),

    EXPIRED_TOKEN(401, "GLOBAL-401-1", "Expired token."),
    INVALID_TOKEN(401, "GLOBAL-401-2", "Invalid token."),
    CREDENTIALS_NOT_FOUND(401, "GLOBAL-401-3", "Credentials not found."),
    INVALID_ROLE(401, "GLOBAL-401-4", "Invalid role."),

    USER_NOT_FOUND(404, "GLOBAL-404-1", "User not found."),
    STUDENT_NOT_FOUND(404, "GLOBAL-404-2", "Student not found."),
    TEACHER_NOT_FOUND(404, "GLOBAL-404-3", "Teacher not found."),
    HR_NOT_FOUND(404, "GLOBAL-404-4", "Hr not found."),
    DOCUMENT_NOT_FOUND(404, "GLOBAL-404-5", "Document not found."),
    FEEDBACK_NOT_FOUND(404, "GLOBAL-404-6", "Feedback not found."),

    INVALID_TYPE(400, "USER-400-1", "Invalid type."),

    OTHER_BAD_REQUEST(400, "OTHER-400-1", "Other Server Bad Request"),
    OTHER_UNAUTHORIZED(401, "OTHER-401-1", "Other Server UnAuthorized"),
    OTHER_FORBIDDEN(403, "OTHER-403-1", "Other Server Forbidden"),
    OTHER_EXPIRED_TOKEN(419, "OTHER-419-1", "Other Server Expired Token")
    ;

    private final int status;
    private final String code;
    private final String message;

}
