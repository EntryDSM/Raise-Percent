package kr.hs.entrydsm.raisepercent.domain.feedback.service;

import kr.hs.entrydsm.raisepercent.domain.feedback.domain.Feedback;
import kr.hs.entrydsm.raisepercent.domain.feedback.domain.repositories.FeedbackRepository;
import kr.hs.entrydsm.raisepercent.domain.feedback.facade.FeedbackFacade;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteFeedbackService {

    private final FeedbackFacade feedbackFacade;
    private final FeedbackRepository feedbackRepository;

    public void execute(String id) {
        Feedback feedback = feedbackFacade.getFeedback(UUIDUtil.convertToUUID(id));
        feedbackRepository.deleteById(feedback.getId());
    }
}
