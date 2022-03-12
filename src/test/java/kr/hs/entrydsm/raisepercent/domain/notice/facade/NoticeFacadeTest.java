package kr.hs.entrydsm.raisepercent.domain.notice.facade;


import kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories.NoticeRepository;
import kr.hs.entrydsm.raisepercent.global.exception.NoticeNotFoundException;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.Notice;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.junit.jupiter.api.Test;


public class NoticeFacadeTest {

    private static final NoticeRepository noticeRepository = mock(NoticeRepository.class);

    private static  final NoticeFacade noticeFacade = new NoticeFacade(noticeRepository);

    private static final String id = "123e4567-e89b-12d3-a456-426614174000";

    private static final String noneExistId = "000e1234-a89c-32a3-a456-426614174000";

    @Test
    void 공지_가져오기() {
        Notice notice = Notice.builder().build();

        when(noticeRepository.findById(UUIDUtil.convertToUUID(id)))
                .thenReturn(Optional.of(notice));

        Notice findNotice = noticeFacade.getNoticeById(id);

        assertEquals(notice,findNotice);
    }

    @Test
    void 공지_존재_예외() {
        when(noticeRepository.findById(UUIDUtil.convertToUUID(noneExistId)))
                .thenReturn(Optional.empty());

        assertThrows(NoticeNotFoundException.class, () -> noticeFacade.getNoticeById(noneExistId));
    }

}
