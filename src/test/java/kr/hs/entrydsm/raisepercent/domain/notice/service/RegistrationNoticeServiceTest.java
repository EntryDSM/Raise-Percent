package kr.hs.entrydsm.raisepercent.domain.notice.service;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories.NoticeRepository;
import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.request.RegistrationNoticeRequest;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.facade.TeacherFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class RegistrationNoticeServiceTest {

    @Mock
    private TeacherFacade teacherFacade;

    @Mock
    private NoticeRepository noticeRepository;

    @InjectMocks
    private RegistrationNoticeService service;

    @Mock
    private RegistrationNoticeRequest request;

    @Test
    void 공지사항_등록() {
        //given
        Teacher teacher = Teacher.builder().build();

        given(teacherFacade.getCurrentTeacher())
                .willReturn(teacher);

        given(request.getScope())
                .willReturn("STUDENT");

        //when
        service.execute(request);

        //then
        then(noticeRepository).should(times(1)).save(any());
    }

}
