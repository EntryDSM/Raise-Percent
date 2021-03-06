package kr.hs.entrydsm.raisepercent.domain.notice.service;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.Notice;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories.NoticeRepository;
import kr.hs.entrydsm.raisepercent.domain.notice.facade.NoticeFacade;
import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.request.UpdateNoticeRequest;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;


public class UpdateNoticeServiceTest {

    private static final NoticeRepository noticeRepository = mock(NoticeRepository.class);

    private static final NoticeFacade noticeFacade = new NoticeFacade(noticeRepository);

    private static final UpdateNoticeService updateNoticeService = new UpdateNoticeService(noticeFacade);

    private static final UpdateNoticeRequest updateNoticeRequest = new UpdateNoticeRequest();

    private static final String id = "123e4567-e89b-12d3-a456-426614174000";

    @Test
    void 공지사항_내부_값_수정() {

        Notice notice = Notice.builder()
                .title("긴급 공지입니다")
                .content("사실 구라입니다")
                .build();

        when(noticeRepository.findById(UUIDUtil.convertToUUID(id)))
                .thenReturn(Optional.of(notice));
        
        updateNoticeService.execute(id,updateNoticeRequest);

        Notice changedNotice = noticeFacade.getNoticeById(id);

        assertEquals(updateNoticeRequest.getTitle(),changedNotice.getTitle());

        assertEquals(updateNoticeRequest.getContent(),changedNotice.getContent());

    }


}
