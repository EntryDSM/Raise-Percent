package kr.hs.entrydsm.raisepercent.domain.notice.presentation;

import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.request.RegistrationNoticeRequest;
import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.response.NoticeDetailsResponse;
import kr.hs.entrydsm.raisepercent.domain.notice.service.RegistrationNoticeService;
import kr.hs.entrydsm.raisepercent.domain.notice.service.ShowNoticeDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/notices")
@RestController
public class NoticeController {

    private final RegistrationNoticeService registrationNoticeService;
    private final ShowNoticeDetailsService showNoticeDetailsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNotice(@RequestBody @Valid RegistrationNoticeRequest request) {
        registrationNoticeService.execute(request);
    }

    @GetMapping("/{notice-id}")
    public NoticeDetailsResponse noticeDetails(@PathVariable("notice-id") String noticeId) {
        return showNoticeDetailsService.execute(noticeId);
    }

}
