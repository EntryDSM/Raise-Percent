package kr.hs.entrydsm.raisepercent.domain.notice.service;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.Notice;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories.NoticeRepository;
import kr.hs.entrydsm.raisepercent.domain.notice.facade.NoticeFacade;
import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.request.UpdateNoticeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateNoticeService {

    private final NoticeFacade noticeFacade;

    public void execute(String noticeId, UpdateNoticeRequest updateNoticeRequest){

        Notice notice = noticeFacade.getNotice(noticeId);

        notice.updateTitle(updateNoticeRequest.getTitle());

        notice.updateContent(updateNoticeRequest.getContent());
    }

}
