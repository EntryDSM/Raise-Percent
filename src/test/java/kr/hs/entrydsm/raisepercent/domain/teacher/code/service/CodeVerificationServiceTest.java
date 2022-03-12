package kr.hs.entrydsm.raisepercent.domain.teacher.code.service;

import kr.hs.entrydsm.raisepercent.domain.teacher.code.domain.Code;
import kr.hs.entrydsm.raisepercent.domain.teacher.code.domain.repositories.CodeRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.code.exception.CodeNotFoundException;
import kr.hs.entrydsm.raisepercent.domain.teacher.code.exception.CodeNotMatchException;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.types.Role;
import kr.hs.entrydsm.raisepercent.domain.teacher.facade.TeacherFacade;
import kr.hs.entrydsm.raisepercent.domain.teacher.code.presentation.dto.request.VerifyTeacherRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CodeVerificationServiceTest {

    private final String codeId = "TEACHERVERIFICATIONCODE";

    private final String codeValue = "codeValue";

    private final Code code = Code.builder().value(codeValue).build();

    @Mock
    private TeacherFacade teacherFacade;

    @Mock
    private CodeRepository codeRepository;

    @InjectMocks
    private CodeVerificationService service;

    @Mock
    private VerifyTeacherRequest request;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(service, "id", codeId);
    }

    @Test
    void 선생님_인증() {
        Teacher teacher = Teacher.builder().build();

        given(teacherFacade.getCurrentTeacher())
                .willReturn(teacher);

        given(codeRepository.findById(codeId))
                .willReturn(Optional.of(code));

        given(request.getCode())
                .willReturn(codeValue);

        service.execute(request);

        assertThat(codeValue).isEqualTo(request.getCode());
        assertThat(teacher.getRole()).isEqualTo(Role.TEACHER);
    }

    @Test
    void 코드_존재하지_않음() {
        //given
        given(codeRepository.findById(codeId))
                .willReturn(Optional.empty());

        //when then
        assertThrows(CodeNotFoundException.class, () -> service.execute(request));
    }

    @Test
    void 코드_일치하지_않음() {
        //given
        given(codeRepository.findById(codeId))
                .willReturn(Optional.of(code));

        given(request.getCode())
                .willReturn("Invalid Code");

        //when then
        assertThrows(CodeNotMatchException.class, () -> service.execute(request));
    }

}
