package kr.hs.entrydsm.raisepercent.domain.code.service;

import kr.hs.entrydsm.raisepercent.domain.code.domain.Code;
import kr.hs.entrydsm.raisepercent.domain.code.domain.repositories.CodeRepository;
import kr.hs.entrydsm.raisepercent.domain.code.exception.CodeNotFoundException;
import kr.hs.entrydsm.raisepercent.domain.code.exception.CodeNotMatchException;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.types.Role;
import kr.hs.entrydsm.raisepercent.domain.teacher.facade.TeacherFacade;
import kr.hs.entrydsm.raisepercent.domain.teacher.presentation.dto.request.VerifyTeacherRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CodeVerificationServiceTest {

    private static final String codeId = "TEACHERVERIFICATIONCODE";

    private static final String codeValue = "codeValue";

    private static final Code code = Code.builder().value(codeValue).build();

    private static final TeacherFacade teacherFacade = mock(TeacherFacade.class);

    private static final CodeRepository codeRepository = mock(CodeRepository.class);

    private static final VerifyTeacherRequest request = mock(VerifyTeacherRequest.class);

    private static final CodeVerificationService service = new CodeVerificationService(teacherFacade, codeRepository);

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(service, "id", codeId);
    }

    @Test
    void 선생님_인증() {
        Teacher teacher = Teacher.builder().build();

        when(teacherFacade.getCurrentTeacher())
                .thenReturn(teacher);

        when(codeRepository.findById(codeId))
                .thenReturn(Optional.of(code));

        when(request.getCode())
                .thenReturn(codeValue);

        service.execute(request);

        assertEquals(codeValue, request.getCode());
        assertEquals(teacher.getRole(), Role.TEACHER);
    }

    @Test
    void 코드_존재하지_않음() {
        when(codeRepository.findById(codeId))
                .thenReturn(Optional.empty());

        assertThrows(CodeNotFoundException.class, () -> service.execute(request));
    }

    @Test
    void 코드_일치하지_않음() {
        when(codeRepository.findById(codeId))
                .thenReturn(Optional.of(code));

        when(request.getCode())
                .thenReturn("Invalid Code");

        assertThrows(CodeNotMatchException.class, () -> service.execute(request));
    }

}
