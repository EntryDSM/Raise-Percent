package kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.response;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.types.Scope;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeDetailsResponse {

    private final String title;
    private final String content;
    private final Scope scope;
    private final LocalDateTime createdAt;
    private final String teacherEmail;
}
