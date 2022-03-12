package kr.hs.entrydsm.raisepercent.domain.feedback.service;

import kr.hs.entrydsm.raisepercent.domain.document.facade.DocumentFacade;
import kr.hs.entrydsm.raisepercent.domain.feedback.domain.repositories.FeedbackRepository;
import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.request.CreateFeedbackRequest;
import kr.hs.entrydsm.raisepercent.domain.teacher.facade.TeacherFacade;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateFeedbackServiceTest {

    @Mock
    private TeacherFacade teacherFacade;

    @Mock
    private DocumentFacade documentFacade;

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private CreateFeedbackRequest request;

    @InjectMocks
    private CreateFeedbackService service;

    @Test
    void 피드백_작성하기() {
        //given
        String id = String.valueOf(UUID.randomUUID());
        String content = "test content";

        given(request.getContent())
                .willReturn(content);

        //when
        service.execute(id, request);

        //then
        then(feedbackRepository).should(times(1)).save(any());
    }
}
