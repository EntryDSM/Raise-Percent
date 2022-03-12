package kr.hs.entrydsm.raisepercent.domain.feedback.service;

import kr.hs.entrydsm.raisepercent.domain.feedback.domain.Feedback;
import kr.hs.entrydsm.raisepercent.domain.feedback.domain.repositories.FeedbackRepository;
import kr.hs.entrydsm.raisepercent.domain.feedback.facade.FeedbackFacade;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class DeleteFeedbackServiceTest {

    @Mock
    private FeedbackFacade feedbackFacade;

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private DeleteFeedbackService service;

    @Test
    void 피드백_삭제하기() {
        //given
        String id = String.valueOf(UUID.randomUUID());

        Feedback feedback = Feedback.builder().build();

        given(feedbackFacade.getFeedback(UUIDUtil.convertToUUID(id)))
                .willReturn(feedback);

        //when
        service.execute(id);

        //then
        then(feedbackRepository).should(times(1)).delete(any());
    }
}
