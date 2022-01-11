package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.notification.domain.Notification;
import kr.hs.entrydsm.raisepercent.domain.notification.domain.exception.NotYourNotificationException;
import kr.hs.entrydsm.raisepercent.domain.notification.domain.exception.NotificationNotFoundException;
import kr.hs.entrydsm.raisepercent.domain.notification.domain.repositories.NotificationRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.facade.UserFacade;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CheckNotificationServiceTest {

    private static final UserFacade userFacade = mock(UserFacade.class);

    private static final NotificationRepository notificationRepository = mock(NotificationRepository.class);

    private static final CheckNotificationService service = new CheckNotificationService(userFacade, notificationRepository);

    @Test
    void 알림_확인() {
        UUID id = UUID.randomUUID();
        User user = User.builder().build();
        Notification notification = Notification.builder()
                .receiver(user)
                .build();

        when(userFacade.getCurrentUser())
                .thenReturn(user);
        when(notificationRepository.findById(id))
                .thenReturn(Optional.of(notification));

        service.execute(id.toString());
    }

    @Test
    void 알림_존재하지_않음() {
        UUID id = UUID.randomUUID();
        User user = User.builder().build();

        when(userFacade.getCurrentUser())
                .thenReturn(user);
        when(notificationRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(NotificationNotFoundException.class, () -> service.execute(id.toString()));
    }

    @Test
    void 자신의_알림이_아님() {
        UUID id = UUID.randomUUID();
        User user = User.builder().build();
        Notification notification = Notification.builder()
                .receiver(User.builder().build())
                .build();

        when(userFacade.getCurrentUser())
                .thenReturn(user);
        when(notificationRepository.findById(id))
                .thenReturn(Optional.of(notification));

        assertThrows(NotYourNotificationException.class, () -> service.execute(id.toString()));
    }

}