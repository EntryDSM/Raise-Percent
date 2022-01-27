package kr.hs.entrydsm.raisepercent.domain.notice.service;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.Notice;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories.NoticeRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

        when(noticeRepository.findById(UUIDUtil.convertToUUID(id)))
                .thenReturn(Optional.of(notice));

        service.execute(id);
    }
}
