package kr.hs.entrydsm.raisepercent.domain.feedback.facade;

import kr.hs.entrydsm.raisepercent.domain.feedback.domain.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FeedbackFacade {

    private final FeedbackRepository feedbackRepository;
}
