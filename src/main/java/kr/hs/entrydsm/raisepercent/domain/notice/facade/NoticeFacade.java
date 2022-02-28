package kr.hs.entrydsm.raisepercent.domain.notice.facade;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.Notice;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories.NoticeRepository;
import kr.hs.entrydsm.raisepercent.global.exception.NoticeNotFoundException;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NoticeFacade {

    private final NoticeRepository noticeRepository;

    public Notice getNotice(String noticeId) {
        return noticeRepository.findById(UUIDUtil.convertToUUID(noticeId))
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);
    }

}
