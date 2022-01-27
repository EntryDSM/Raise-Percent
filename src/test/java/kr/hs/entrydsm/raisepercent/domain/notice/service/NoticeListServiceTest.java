package kr.hs.entrydsm.raisepercent.domain.notice.service;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.Notice;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories.NoticeRepository;
import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.response.NoticeListResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NoticeListServiceTest {

    private static final NoticeRepository noticeRepository = mock(NoticeRepository.class);

    private static final NoticeListService service = new NoticeListService(noticeRepository);

    @Test
    void 공지사항_목록() {
        List<Notice> arrayList = new ArrayList<>();

        when(noticeRepository.findAll())
                .thenReturn(arrayList);

        NoticeListResponse response = service.execute();

        assertThat(arrayList).isEqualTo(response.getNoticeList());

        verify(noticeRepository, times(1)).findAll();
    }
}
