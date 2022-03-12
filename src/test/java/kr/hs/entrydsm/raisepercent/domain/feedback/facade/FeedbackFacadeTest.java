package kr.hs.entrydsm.raisepercent.domain.feedback.facade;

import kr.hs.entrydsm.raisepercent.domain.feedback.domain.Feedback;
import kr.hs.entrydsm.raisepercent.domain.feedback.domain.repositories.FeedbackRepository;
import kr.hs.entrydsm.raisepercent.global.exception.FeedbackNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class FeedbackFacadeTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackFacade feedbackFacade;

    @Test
    void 피드백_정보_가져오기() {
        //given
        UUID id = UUID.randomUUID();

        Feedback feedback = Feedback.builder().build();

        given(feedbackRepository.findById(id))
                .willReturn(Optional.of(feedback));

        //when
        Feedback getFeedback = feedbackFacade.getFeedback(id);

        //then
        assertThat(getFeedback).isEqualTo(feedback);
    }

    @Test
    void 피드백_정보_가져오기_실패() {
        //given
        UUID id = UUID.randomUUID();

        given(feedbackRepository.findById(id))
                .willReturn(Optional.empty());

        //when then
        assertThrows(FeedbackNotFoundException.class, () -> feedbackFacade.getFeedback(id));
    }
}
