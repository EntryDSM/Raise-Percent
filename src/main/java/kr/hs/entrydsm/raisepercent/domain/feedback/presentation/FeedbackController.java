package kr.hs.entrydsm.raisepercent.domain.feedback.presentation;

import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.request.CreateFeedbackRequest;
import kr.hs.entrydsm.raisepercent.domain.feedback.service.CreateFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/feedback")
@RestController
public class FeedbackController {

    private final CreateFeedbackService createFeedbackService;

    @PostMapping("/{document-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createFeedback(@PathVariable("document-id") UUID id,
                               @RequestBody @Valid CreateFeedbackRequest request) {
        createFeedbackService.execute(id, request);
    }
}
