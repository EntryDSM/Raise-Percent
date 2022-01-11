package kr.hs.entrydsm.raisepercent.domain.feedback.service;

import kr.hs.entrydsm.raisepercent.domain.feedback.domain.Feedback;
import kr.hs.entrydsm.raisepercent.domain.feedback.facade.FeedbackFacade;
import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.request.UpdateFeedbackRequest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateFeedbackServiceTest {

    private static final FeedbackFacade feedbackFacade = mock(FeedbackFacade.class);

    private static final UpdateFeedbackRequest request = mock(UpdateFeedbackRequest.class);

    private static final UpdateFeedbackService service = new UpdateFeedbackService(feedbackFacade);

    @Test
    void 피드백_수정하기() {
        String id = String.valueOf(UUID.randomUUID());
        String content = "Test Content";

        Feedback feedback = Feedback.builder().build();

        when(feedbackFacade.getFeedback(UUID.fromString(id)))
                .thenReturn(feedback);
        when(request.getContent())
                .thenReturn(content);

        service.execute(id, request);

        assertThat(content).isEqualTo(feedback.getContent());
    }
}
