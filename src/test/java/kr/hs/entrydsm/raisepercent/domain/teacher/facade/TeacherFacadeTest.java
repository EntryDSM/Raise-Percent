package kr.hs.entrydsm.raisepercent.domain.teacher.facade;

import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.repositories.TeacherRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.types.Role;
import kr.hs.entrydsm.raisepercent.global.exception.TeacherNotFoundException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TeacherFacadeTest {
    final String email = "test@dsm.hs.kr";

    @Mock
    private AuthFacade authFacade;

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherFacade teacherFacade;

    @Test
    void 선생님_정보_가져오기() {
        //given
        Teacher teacher = Teacher.builder()
                .role(Role.TEACHER)
                .build();

        given(authFacade.getCurrentDetails())
                .willReturn(new AuthDetails(email, Type.TEACHER));
        given(teacherRepository.findById(email))
                .willReturn(Optional.of(teacher));

        //when then
        assertThat(teacher).isEqualTo(teacherFacade.getCurrentTeacher());
    }

    @Test
    void 선생님_정보_예외() {
        //given
        given(authFacade.getCurrentDetails())
                .willReturn(new AuthDetails(email, Type.TEACHER));
        given(teacherRepository.findById(anyString()))
                .willReturn(Optional.empty());

        //when then
        assertThrows(TeacherNotFoundException.class, teacherFacade::getCurrentTeacher);
    }

}
