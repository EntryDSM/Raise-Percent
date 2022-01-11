package kr.hs.entrydsm.raisepercent.domain.feedback.presentation;

import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.request.CreateFeedbackRequest;
import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.request.UpdateFeedbackRequest;
import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.response.ShowFeedbackResponse;
import kr.hs.entrydsm.raisepercent.domain.feedback.service.CreateFeedbackService;
import kr.hs.entrydsm.raisepercent.domain.feedback.service.ShowFeedbackService;
import kr.hs.entrydsm.raisepercent.domain.feedback.service.UpdateFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/feedback")
@RestController
public class FeedbackController {

    private final CreateFeedbackService createFeedbackService;
    private final ShowFeedbackService showFeedbackService;
    private final UpdateFeedbackService updateFeedbackService;

    @PostMapping("/{document-id}")
    public void createFeedback(@PathVariable("document-id") String id,
                               @RequestBody @Valid CreateFeedbackRequest request) {
        createFeedbackService.execute(id, request);
    }

    @GetMapping("/{feedback-id}")
    public ShowFeedbackResponse showFeedback(@PathVariable("feedback-id") String id) {
        return showFeedbackService.execute(id);
    }

    @PatchMapping("/{feedback-id}")
    public ShowFeedbackResponse updateFeedback(@PathVariable("feedback-id") String id,
                                               @RequestBody @Valid UpdateFeedbackRequest request) {
        return updateFeedbackService.execute(id, request);
    }
}
