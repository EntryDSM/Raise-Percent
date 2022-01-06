package kr.hs.entrydsm.raisepercent.domain.feedback.service;

import kr.hs.entrydsm.raisepercent.domain.feedback.domain.repositories.FeedbackRepository;
import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.request.CreateFeedbackRequest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class CreateFeedbackServiceTest {

    private static final CreateFeedbackService service = mock(CreateFeedbackService.class);

    private static final CreateFeedbackRequest request = mock(CreateFeedbackRequest.class);

    private static final FeedbackRepository feedbackRepository = mock(FeedbackRepository.class);

    @Test
    void 피드백_작성하기() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String content = "test content";

        when(request.getContent())
                .thenReturn(content);

        service.execute(id, request);

        verify(feedbackRepository, times(1));
    }
}
