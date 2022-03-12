package kr.hs.entrydsm.raisepercent.domain.feedback.service;

import kr.hs.entrydsm.raisepercent.domain.feedback.domain.Feedback;
import kr.hs.entrydsm.raisepercent.domain.feedback.facade.FeedbackFacade;
import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.request.UpdateFeedbackRequest;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UpdateFeedbackServiceTest {

    @Mock
    private FeedbackFacade feedbackFacade;

    @Mock
    private UpdateFeedbackRequest request;

    @InjectMocks
    private UpdateFeedbackService service;

    @Test
    void 피드백_수정하기() {
        //given
        String id = String.valueOf(UUID.randomUUID());
        String content = "Test Content";

        Feedback feedback = Feedback.builder().build();

        given(feedbackFacade.getFeedback(UUIDUtil.convertToUUID(id)))
                .willReturn(feedback);
        given(request.getContent())
                .willReturn(content);

        //when
        service.execute(id, request);

        //then
        assertThat(content).isEqualTo(feedback.getContent());
    }
}
