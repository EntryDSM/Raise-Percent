package kr.hs.entrydsm.raisepercent.domain.feedback.facade;

import kr.hs.entrydsm.raisepercent.domain.feedback.domain.Feedback;
import kr.hs.entrydsm.raisepercent.domain.feedback.domain.repositories.FeedbackRepository;
import kr.hs.entrydsm.raisepercent.global.exception.FeedbackNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class FeedbackFacade {

    private final FeedbackRepository feedbackRepository;

    public Feedback getFeedback(UUID id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> FeedbackNotFoundException.EXCEPTION);
    }
}
