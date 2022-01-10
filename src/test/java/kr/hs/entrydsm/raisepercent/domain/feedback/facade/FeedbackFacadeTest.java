package kr.hs.entrydsm.raisepercent.domain.feedback.facade;

import kr.hs.entrydsm.raisepercent.domain.feedback.domain.Feedback;
import kr.hs.entrydsm.raisepercent.domain.feedback.domain.repositories.FeedbackRepository;
import kr.hs.entrydsm.raisepercent.global.exception.FeedbackNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FeedbackFacadeTest {

    private static final FeedbackRepository feedbackRepository = mock(FeedbackRepository.class);

    private static final FeedbackFacade feedbackFacade = new FeedbackFacade(feedbackRepository);

    @Test
    void 피드백_정보_가져오기() {
        UUID id = UUID.randomUUID();

        Feedback feedback = Feedback.builder().build();

        when(feedbackRepository.findById(id))
                .thenReturn(Optional.of(feedback));

        Feedback getFeedback = feedbackFacade.getFeedback(id);

        assertThat(getFeedback).isEqualTo(feedback);
    }

    @Test
    void 피드백_정보_가져오기_실패() {
        UUID id = UUID.randomUUID();

        when(feedbackRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(FeedbackNotFoundException.class, () -> feedbackFacade.getFeedback(id));
    }
}
