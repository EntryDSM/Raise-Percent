package kr.hs.entrydsm.raisepercent.domain.feedback.service;

import kr.hs.entrydsm.raisepercent.domain.feedback.domain.Feedback;
import kr.hs.entrydsm.raisepercent.domain.feedback.domain.repositories.FeedbackRepository;
import kr.hs.entrydsm.raisepercent.domain.feedback.facade.FeedbackFacade;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteFeedbackServiceTest {

    private static final FeedbackFacade feedbackFacade = mock(FeedbackFacade.class);

    private static final FeedbackRepository feedbackRepository = mock(FeedbackRepository.class);

    private static final DeleteFeedbackService service = new DeleteFeedbackService(feedbackFacade, feedbackRepository);

    @Test
    void 피드백_삭제하기() {
        String id = String.valueOf(UUID.randomUUID());

        Feedback feedback = Feedback.builder().build();

        when(feedbackFacade.getFeedback(UUIDUtil.convertToUUID(id)))
                .thenReturn(feedback);

        service.execute(id);

        verify(feedbackRepository, times(1)).delete(any());
    }
}
