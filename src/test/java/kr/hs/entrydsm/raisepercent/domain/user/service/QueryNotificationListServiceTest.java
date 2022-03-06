package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.notification.domain.facade.NotificationFacade;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.facade.UserFacade;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.response.NotificationListResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QueryNotificationListServiceTest {

    @Mock
    private UserFacade userFacade;

    @Mock
    private NotificationFacade notificationFacade;

    @InjectMocks
    private QueryNotificationListService service;

    @Test
    void 알림_리스트_가져오기() {
        //given
        User user = User.builder().build();
        Pageable pageable = PageRequest.of(5, 5);
        NotificationListResponse response = new NotificationListResponse(new ArrayList<>());

        when(userFacade.getCurrentUser())
                .thenReturn(user);
        when(notificationFacade.queryNotificationElement(user, pageable))
                .thenReturn(new ArrayList<>());

        //when
        NotificationListResponse execute = service.execute(pageable);

        assertThat(execute).usingRecursiveComparison().isEqualTo(response);
    }


}