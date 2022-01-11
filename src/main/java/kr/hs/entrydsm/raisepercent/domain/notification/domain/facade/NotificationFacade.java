package kr.hs.entrydsm.raisepercent.domain.notification.domain.facade;

import kr.hs.entrydsm.raisepercent.domain.notification.domain.repositories.NotificationRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.response.NotificationElement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class NotificationFacade {

    private final NotificationRepository notificationRepository;

    public List<NotificationElement> queryNotificationElement(User receiver, Pageable pageable) {
        return notificationRepository.findByReceiver(receiver, pageable).stream().map(
                notification -> NotificationElement.builder()
                        .notificationId(notification.getId().toString())
                        .icon(notification.getIcon())
                        .title(notification.getTitle())
                        .content(notification.getContent())
                        .createdAt(notification.getCreatedAt())
                        .isWatch(notification.isWatch())
                        .type(notification.getType().toString())
                        .value(notification.getValue())
                        .build()
        ).collect(Collectors.toList());
    }

}
