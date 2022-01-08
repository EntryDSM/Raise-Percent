package kr.hs.entrydsm.raisepercent.domain.feedback.service;

import kr.hs.entrydsm.raisepercent.domain.feedback.domain.Feedback;
import kr.hs.entrydsm.raisepercent.domain.feedback.facade.FeedbackFacade;
import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.response.ShowFeedbackResponse;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ShowFeedbackService {

    private final FeedbackFacade feedbackFacade;
    private final AuthFacade authFacade;
    private final StudentFacade studentFacade;

    public ShowFeedbackResponse execute(UUID id) {
        Feedback feedback = feedbackFacade.getFeedback(id);

        if (authFacade.getCurrentDetails().getRole() == Type.STUDENT) {
            if (!feedback.getDocument().getStudent().equals(studentFacade.getCurrentStudent())) {
                throw InvalidRoleException.EXCEPTION;
            }
        }

        return new ShowFeedbackResponse(feedback.getContent());
    }

}
