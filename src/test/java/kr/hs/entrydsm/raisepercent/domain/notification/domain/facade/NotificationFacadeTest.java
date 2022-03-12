package kr.hs.entrydsm.raisepercent.domain.notification.domain.facade;

import kr.hs.entrydsm.raisepercent.domain.notification.domain.Notification;
import kr.hs.entrydsm.raisepercent.domain.notification.domain.repositories.NotificationRepository;
import kr.hs.entrydsm.raisepercent.domain.notification.domain.types.Type;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationFacadeTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private Notification notification;

    @InjectMocks
    private NotificationFacade notificationFacade;

    @Test
    void 알림_원소_가져오기() {
        //given
        User receiver = User.builder().build();
        Pageable pageable = PageRequest.of(5, 5);

        List<Notification> notificationList = List.of(notification);

        given(notification.getId())
                .willReturn(UUID.randomUUID());
        given(notification.getDocumentType())
                .willReturn(Type.COMPANY);
        given(notificationRepository.findByReceiver(receiver, pageable))
                .willReturn(notificationList);

        //when
        notificationFacade.queryNotificationElement(receiver, pageable);
    }

}