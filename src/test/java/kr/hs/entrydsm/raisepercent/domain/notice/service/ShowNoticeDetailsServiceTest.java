package kr.hs.entrydsm.raisepercent.domain.notice.service;

import kr.hs.entrydsm.raisepercent.domain.hr.domain.repositories.HrRepository;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.Notice;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories.NoticeRepository;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.types.Scope;
import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.response.NoticeDetailsResponse;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.facade.UserFacade;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import kr.hs.entrydsm.raisepercent.global.exception.NoticeNotFoundException;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ShowNoticeDetailsServiceTest {

    private static final NoticeRepository noticeRepository = mock(NoticeRepository.class);

    private static final UserFacade userFacade = mock(UserFacade.class);

    private static final HrRepository hrRepository = mock(HrRepository.class);

    private static final StudentRepository studentRepository = mock(StudentRepository.class);

    private static final ShowNoticeDetailsService service =
            new ShowNoticeDetailsService(noticeRepository, userFacade, hrRepository, studentRepository);

    @Test
    void 공지사항_상세보기() {
        String id = String.valueOf(UUID.randomUUID());
        String email = "test@test.com";

        Teacher teacher = Teacher.builder().build();

        Notice notice = Notice.builder()
                .teacher(teacher)
                .scope(Scope.STUDENT)
                .build();

        User user = User.builder()
                .email(email)
                .build();

        Student student = Student.builder()
                .user(user)
                .build();

        given(userFacade.getCurrentUser())
                .willReturn(user);
        given(studentRepository.findById(user.getEmail()))
                .willReturn(Optional.of(student));
        given(noticeRepository.findById(UUIDUtil.convertToUUID(id)))
                .willReturn(Optional.of(notice));

        NoticeDetailsResponse response = service.execute(id);

        assertThat(response.getTitle()).isEqualTo(notice.getTitle());
        assertThat(response.getContent()).isEqualTo(notice.getContent());
        assertThat(response.getScope()).isEqualTo(notice.getScope());
        assertThat(response.getCreatedAt()).isEqualTo(notice.getCreatedAt());
        assertThat(response.getTeacherEmail()).isEqualTo(notice.getTeacher().getEmail());
    }

    @Test
    void 공지사항_스코프_예외_학생() {
        String id = String.valueOf(UUID.randomUUID());
        String email = "test@test.com";

        Teacher teacher = Teacher.builder().build();

        Notice notice = Notice.builder()
                .teacher(teacher)
                .scope(Scope.STUDENT)
                .build();

        User user = User.builder()
                .email(email)
                .build();

        given(userFacade.getCurrentUser())
                .willReturn(user);
        given(studentRepository.findById(user.getEmail()))
                .willReturn(Optional.empty());
        given(noticeRepository.findById(UUIDUtil.convertToUUID(id)))
                .willReturn(Optional.of(notice));

        assertThrows(InvalidRoleException.class, () -> service.execute(id));
    }

    @Test
    void 공지사항_스코프_예외_회사() {
        String id = String.valueOf(UUID.randomUUID());
        String email = "test@test.com";

        Teacher teacher = Teacher.builder().build();

        Notice notice = Notice.builder()
                .teacher(teacher)
                .scope(Scope.COMPANY)
                .build();

        User user = User.builder()
                .email(email)
                .build();

        given(userFacade.getCurrentUser())
                .willReturn(user);
        given(hrRepository.findById(user.getEmail()))
                .willReturn(Optional.empty());
        given(noticeRepository.findById(UUIDUtil.convertToUUID(id)))
                .willReturn(Optional.of(notice));

        assertThrows(InvalidRoleException.class, () -> service.execute(id));
    }

    @Test
    void 공시사항_존재하지_않음_예외() {
        String id = String.valueOf(UUID.randomUUID());

        given(noticeRepository.findById(UUIDUtil.convertToUUID(id)))
                .willReturn(Optional.empty());

        assertThrows(NoticeNotFoundException.class, () -> service.execute(id));

    }
}
