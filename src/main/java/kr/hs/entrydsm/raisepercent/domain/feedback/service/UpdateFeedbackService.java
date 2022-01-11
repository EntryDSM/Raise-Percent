package kr.hs.entrydsm.raisepercent.domain.feedback.service;

import kr.hs.entrydsm.raisepercent.domain.feedback.domain.Feedback;
import kr.hs.entrydsm.raisepercent.domain.feedback.facade.FeedbackFacade;
import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.request.UpdateFeedbackRequest;
import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.response.ShowFeedbackResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdateFeedbackService {

    private final FeedbackFacade feedbackFacade;

    @Transactional
    public ShowFeedbackResponse execute(String id, UpdateFeedbackRequest request) {

        Feedback feedback = feedbackFacade.getFeedback(UUID.fromString(id));
        feedback.updateContent(request.getContent());
        return new ShowFeedbackResponse(feedback.getContent());
    }

}
