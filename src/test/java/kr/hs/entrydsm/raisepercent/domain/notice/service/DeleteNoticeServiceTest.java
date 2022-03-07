package kr.hs.entrydsm.raisepercent.domain.notice.service;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.Notice;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories.NoticeRepository;
import kr.hs.entrydsm.raisepercent.domain.notice.facade.NoticeFacade;
import kr.hs.entrydsm.raisepercent.global.exception.NoticeNotFoundException;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class DeleteNoticeServiceTest {

    private static final NoticeRepository noticeRepository = mock(NoticeRepository.class);

    private static final NoticeFacade noticeFacade = mock(NoticeFacade.class);

    private static final DeleteNoticeService deleteNoticeService =
            new DeleteNoticeService(noticeFacade,noticeRepository);

    private static final String id = "123e4567-e89b-12d3-a456-426614174000";

    @Test
    public void 공지사항_삭제() {
        Notice notice = Notice.builder().build();

        when(noticeRepository.findById(UUIDUtil.convertToUUID(id)))
                .thenReturn(Optional.of(notice));

        deleteNoticeService.execute(id);

        when(noticeFacade.getNoticeById(id))
                .thenThrow(NoticeNotFoundException.EXCEPTION);

    }

}
