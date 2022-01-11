package kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NotificationListResponse {

    private final List<NotificationElement> notificationList;

}
