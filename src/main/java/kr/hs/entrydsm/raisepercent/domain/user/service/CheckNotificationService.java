package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.notification.domain.Notification;
import kr.hs.entrydsm.raisepercent.domain.notification.domain.exception.NotYourNotificationException;
import kr.hs.entrydsm.raisepercent.domain.notification.domain.exception.NotificationNotFoundException;
import kr.hs.entrydsm.raisepercent.domain.notification.domain.repositories.NotificationRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CheckNotificationService {

    private final UserFacade userFacade;
    private final NotificationRepository notificationRepository;

    @Transactional
    public void execute(String notificationId) {
        User receiver = userFacade.getCurrentUser();

        Notification notification = notificationRepository.findById(UUID.fromString(notificationId))
                .orElseThrow(() -> NotificationNotFoundException.EXCEPTION);

        if(!notification.getReceiver().equals(receiver)) {
            throw NotYourNotificationException.EXCEPTION;
        }

        notification.checkNotification();
    }

}