package kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationElement {

    private final String notificationId;
    private final String icon;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final boolean isWatch;
    private final String type;
    private final String value;

}
