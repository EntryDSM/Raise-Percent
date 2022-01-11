package kr.hs.entrydsm.raisepercent.domain.notice.service;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories.NoticeRepository;
import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.request.RegistrationNoticeRequest;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.facade.TeacherFacade;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RegistrationNoticeServiceTest {

    private static final TeacherFacade teacherFacade = mock(TeacherFacade.class);

    private static final NoticeRepository noticeRepository = mock(NoticeRepository.class);

    private static final RegistrationNoticeService service = new RegistrationNoticeService(teacherFacade, noticeRepository);

    private static final RegistrationNoticeRequest request = mock(RegistrationNoticeRequest.class);

    @Test
    void 공지사항_등록() {
        Teacher teacher = Teacher.builder().build();

        when(teacherFacade.getCurrentTeacher())
                .thenReturn(teacher);

        when(request.getScope())
                .thenReturn("STUDENT");

        service.execute(request);

        verify(noticeRepository, times(1)).save(any());
    }

}
