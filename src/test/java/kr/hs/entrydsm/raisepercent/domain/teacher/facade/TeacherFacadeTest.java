package kr.hs.entrydsm.raisepercent.domain.teacher.facade;

import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.repositories.TeacherRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.types.Role;
import kr.hs.entrydsm.raisepercent.global.exception.TeacherNotFoundException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.auth.detail.AuthDetails;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TeacherFacadeTest {
    final String email = "test@dsm.hs.kr";

    private static final AuthFacade authFacade = mock(AuthFacade.class);

    private static final TeacherRepository teacherRepository = mock(TeacherRepository.class);

    private static final TeacherFacade teacherFacade = new TeacherFacade(authFacade, teacherRepository);


    @Test
    void 선생님_정보_가져오기() {
        Teacher teacher = Teacher.builder()
                .role(Role.TEACHER)
                .build();

        when(authFacade.getCurrentDetails())
                .thenReturn(new AuthDetails(email, Type.TEACHER));
        when(teacherRepository.findById(email))
                .thenReturn(Optional.of(teacher));

        assertEquals(teacher, teacherFacade.getCurrentTeacher());
    }

    @Test
    void 선생님_정보_예외() {
        when(authFacade.getCurrentDetails())
                .thenReturn(new AuthDetails(email, Type.TEACHER));
        when(teacherRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(TeacherNotFoundException.class, teacherFacade::getCurrentTeacher);
    }

}
