package kr.hs.entrydsm.raisepercent.domain.notice.presentation;

import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.request.RegistrationNoticeRequest;
import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.request.UpdateNoticeRequest;
import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.response.NoticeDetailsResponse;
import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.response.NoticeListResponse;
import kr.hs.entrydsm.raisepercent.domain.notice.service.NoticeListService;
import kr.hs.entrydsm.raisepercent.domain.notice.service.RegistrationNoticeService;
import kr.hs.entrydsm.raisepercent.domain.notice.service.ShowNoticeDetailsService;
import kr.hs.entrydsm.raisepercent.domain.notice.service.UpdateNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/notices")
@RestController
public class NoticeController {

    private final RegistrationNoticeService registrationNoticeService;
    private final ShowNoticeDetailsService showNoticeDetailsService;
    private final NoticeListService noticeListService;
    private final UpdateNoticeService updateNoticeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNotice(@RequestBody @Valid RegistrationNoticeRequest request) {
        registrationNoticeService.execute(request);
    }

    @GetMapping("/{notice-id}")
    public NoticeDetailsResponse noticeDetails(@PathVariable("notice-id") String noticeId) {
        return showNoticeDetailsService.execute(noticeId);
    }
    
    @GetMapping
    public NoticeListResponse noticeList() {
        return noticeListService.execute();
    }

    @PatchMapping("/{notice-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNotice(@PathVariable("notice-id")String noticeId, @RequestBody UpdateNoticeRequest updateNoticeRequest){
        updateNoticeService.execute(noticeId,updateNoticeRequest);
    }

}
