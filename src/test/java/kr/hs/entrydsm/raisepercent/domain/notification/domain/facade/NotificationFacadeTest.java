package kr.hs.entrydsm.raisepercent.domain.notification.domain.facade;

import kr.hs.entrydsm.raisepercent.domain.notification.domain.Notification;
import kr.hs.entrydsm.raisepercent.domain.notification.domain.repositories.NotificationRepository;
import kr.hs.entrydsm.raisepercent.domain.notification.domain.types.Type;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NotificationFacadeTest {

    private static final NotificationRepository notificationRepository = mock(NotificationRepository.class);

    private static final Notification notification = mock(Notification.class);

    private static final NotificationFacade notificationFacade = new NotificationFacade(notificationRepository);

    @Test
    void 알림_원소_가져오기() {
        User receiver = User.builder().build();
        Pageable pageable = PageRequest.of(5, 5);

        List<Notification> notificationList = List.of(notification);

        when(notification.getId())
                .thenReturn(UUID.randomUUID());
        when(notification.getDocumentType())
                .thenReturn(Type.COMPANY);
        when(notificationRepository.findByReceiver(receiver, pageable))
                .thenReturn(notificationList);

        notificationFacade.queryNotificationElement(receiver, pageable);
    }

}