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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CheckNotificationServiceTest {

    @Mock
    private UserFacade userFacade;

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private CheckNotificationService service;

    @Test
    void 알림_확인() {
        //given
        UUID id = UUID.randomUUID();
        User user = User.builder().build();
        Notification notification = Notification.builder()
                .isWatch(false)
                .receiver(user)
                .build();

        given(userFacade.getCurrentUser())
                .willReturn(user);
        given(notificationRepository.findById(id))
                .willReturn(Optional.of(notification));

        //when
        service.execute(id.toString());

        //then
        assertThat(notification.isWatch()).isTrue();
    }

    @Test
    void 알림_존재하지_않음() {
        //given
        UUID id = UUID.randomUUID();
        User user = User.builder().build();

        given(userFacade.getCurrentUser())
                .willReturn(user);
        given(notificationRepository.findById(id))
                .willReturn(Optional.empty());

        //when then
        assertThrows(NotificationNotFoundException.class, () -> service.execute(id.toString()));
    }

    @Test
    void 자신의_알림이_아님() {
        //given
        UUID id = UUID.randomUUID();
        User user = User.builder().build();
        Notification notification = Notification.builder()
                .receiver(User.builder().build())
                .build();

        given(userFacade.getCurrentUser())
                .willReturn(user);
        given(notificationRepository.findById(id))
                .willReturn(Optional.of(notification));

        //when then
        assertThrows(NotYourNotificationException.class, () -> service.execute(id.toString()));
    }

}