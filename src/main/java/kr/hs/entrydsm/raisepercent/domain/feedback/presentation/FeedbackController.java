package kr.hs.entrydsm.raisepercent.domain.feedback.presentation;

import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.request.CreateFeedbackRequest;
import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.response.ShowFeedbackResponse;
import kr.hs.entrydsm.raisepercent.domain.feedback.service.CreateFeedbackService;
import kr.hs.entrydsm.raisepercent.domain.feedback.service.ShowFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/feedback")
@RestController
public class FeedbackController {

    private final CreateFeedbackService createFeedbackService;
    private final ShowFeedbackService showFeedbackService;

    @PostMapping("/{document-id}")
    public void createFeedback(@PathVariable("document-id") UUID id,
                               @RequestBody @Valid CreateFeedbackRequest request) {
        createFeedbackService.execute(id, request);
    }

    @GetMapping("/{feedback-id}")
    public ShowFeedbackResponse showFeedback(@PathVariable("feedback-id") UUID id) {
        return showFeedbackService.execute(id);
    }
}
