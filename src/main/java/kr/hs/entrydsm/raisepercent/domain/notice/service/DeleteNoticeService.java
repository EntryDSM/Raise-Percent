package kr.hs.entrydsm.raisepercent.domain.notice.service;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.Notice;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories.NoticeRepository;
import kr.hs.entrydsm.raisepercent.domain.notice.facade.NoticeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteNoticeService {

    private final NoticeFacade noticeFacade;
    private final NoticeRepository noticeRepository;

    @Transactional
    public void execute(String noticeId) {

        Notice notice = noticeFacade.getNoticeById(noticeId);

        noticeRepository.delete(notice);

    }

}
