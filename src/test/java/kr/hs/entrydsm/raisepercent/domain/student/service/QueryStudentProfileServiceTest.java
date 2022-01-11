package kr.hs.entrydsm.raisepercent.domain.student.service;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.domain.types.Position;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.domain.tag.facade.TagFacade;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.global.exception.StudentNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QueryStudentProfileServiceTest {

    private static final StudentFacade studentFacade = mock(StudentFacade.class);

    private static final TagFacade tagFacade = mock(TagFacade.class);

    private static final QueryStudentProfileService service = new QueryStudentProfileService(studentFacade, tagFacade);

    @Test
    void 학생_존재하지않음() {
        when(studentFacade.getStudent(anyString()))
                .thenThrow(StudentNotFoundException.class);

        assertThrows(StudentNotFoundException.class, () -> {
            service.execute("test@gmail.com");
        });
    }

    @Test
    void 학생_정보_가져오기() {
        String email = "test@gmail.com";
        User user = User.builder().email(email).build();
        Student student = Student.builder()
                .user(user)
                .build();
        student.updatePosition(Position.BACKEND);

        when(studentFacade.getStudent(email))
                .thenReturn(student);
        when(tagFacade.queryRegisteredTagValue(student))
                .thenReturn(List.of());

        service.execute(email);
    }

}