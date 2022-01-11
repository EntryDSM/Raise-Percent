package kr.hs.entrydsm.raisepercent.domain.feedback.service;

import kr.hs.entrydsm.raisepercent.domain.document.facade.DocumentFacade;
import kr.hs.entrydsm.raisepercent.domain.feedback.domain.repositories.FeedbackRepository;
import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.request.CreateFeedbackRequest;
import kr.hs.entrydsm.raisepercent.domain.teacher.facade.TeacherFacade;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class CreateFeedbackServiceTest {

    private static final TeacherFacade teacherFacade = mock(TeacherFacade.class);

    private static final DocumentFacade documentFacade = mock(DocumentFacade.class);

    private static final FeedbackRepository feedbackRepository = mock(FeedbackRepository.class);

    private static final CreateFeedbackRequest request = mock(CreateFeedbackRequest.class);

    private static final CreateFeedbackService service = new CreateFeedbackService(teacherFacade, documentFacade, feedbackRepository);

    @Test
    void 피드백_작성하기() {
        String id = String.valueOf(UUID.randomUUID());
        String content = "test content";

        when(request.getContent())
                .thenReturn(content);

        service.execute(id, request);

        verify(feedbackRepository, times(1)).save((any()));
    }
}
