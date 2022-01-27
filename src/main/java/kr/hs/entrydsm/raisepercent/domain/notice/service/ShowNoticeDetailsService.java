package kr.hs.entrydsm.raisepercent.domain.notice.service;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.Notice;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories.NoticeRepository;
import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.response.NoticeDetailsResponse;
import kr.hs.entrydsm.raisepercent.global.exception.NoticeNotFoundException;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ShowNoticeDetailsService {

    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public NoticeDetailsResponse execute(String noticeId) {
        Notice notice = noticeRepository.findById(UUIDUtil.convertToUUID(noticeId))
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        return NoticeDetailsResponse.builder()
                .title(notice.getTitle())
                .content(notice.getContent())
                .scope(notice.getScope())
                .createdAt(notice.getCreatedAt())
                .teacherEmail(notice.getTeacher().getEmail())
                .build();
    }
}
