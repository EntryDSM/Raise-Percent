package kr.hs.entrydsm.raisepercent.domain.notice.service;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.Notice;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories.NoticeRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.global.exception.NoticeNotFoundException;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ShowNoticeDetailsServiceTest {

    private static final NoticeRepository noticeRepository = mock(NoticeRepository.class);

    private static final ShowNoticeDetailsService service = new ShowNoticeDetailsService(noticeRepository);

    @Test
    void 공지사항_상세보기() {
        String id = String.valueOf(UUID.randomUUID());

        Teacher teacher = Teacher.builder().build();

        Notice notice = Notice.builder()
                .teacher(teacher)
                .build();

        given(noticeRepository.findById(UUIDUtil.convertToUUID(id)))
                .willReturn(Optional.of(notice));

        service.execute(id);
    }

    @Test
    void 공시사항_존재하지_않음_예외() {
        String id = String.valueOf(UUID.randomUUID());

        given(noticeRepository.findById(UUIDUtil.convertToUUID(id)))
                .willReturn(Optional.empty());

        assertThrows(NoticeNotFoundException.class, () -> service.execute(id));

    }
}
