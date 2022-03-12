package kr.hs.entrydsm.raisepercent.domain.student.service;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.domain.types.Position;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response.ProfileResponse;
import kr.hs.entrydsm.raisepercent.domain.tag.facade.TagFacade;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.global.exception.StudentNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class QueryStudentProfileServiceTest {

    @Mock
    private StudentFacade studentFacade;

    @Mock
    private TagFacade tagFacade;

    @InjectMocks
    private QueryStudentProfileService service;

    @Test
    void 학생_존재하지않음() {
        //given
        given(studentFacade.getStudent(anyString()))
                .willThrow(StudentNotFoundException.class);

        //when then
        assertThrows(StudentNotFoundException.class, () -> {
            service.execute("test@gmail.com");
        });
    }

    @Test
    void 학생_정보_가져오기() {
        //given
        String email = "test@gmail.com";
        String contactEmail = "contactEmail@gmail.com";
        String name = "name";
        Position position = Position.BACKEND;
        String contactTel = "01012341234";

        User user = User.builder()
                .email(email)
                .name(name)
                .build();
        user.setContactEmail(contactEmail);
        user.setContactTel(contactTel);

        Student student = Student.builder()
                .user(user)
                .build();
        student.updatePosition(position);

        given(studentFacade.getStudent(email))
                .willReturn(student);
        given(tagFacade.queryRegisteredTagValue(student))
                .willReturn(List.of());

        ProfileResponse response = new ProfileResponse(name, contactEmail, contactTel, position.name(), List.of());

        //when
        ProfileResponse execute = service.execute(email);

        //then
        assertThat(execute).usingRecursiveComparison().isEqualTo(response);
    }

}