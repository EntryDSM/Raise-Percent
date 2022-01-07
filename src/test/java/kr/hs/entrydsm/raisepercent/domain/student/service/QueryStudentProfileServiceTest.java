package kr.hs.entrydsm.raisepercent.domain.student.service;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.domain.tag.facade.TagFacade;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.global.exception.StudentNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QueryStudentProfileServiceTest {

    private static final StudentRepository studentRepository = mock(StudentRepository.class);

    private static final TagFacade tagFacade = mock(TagFacade.class);

    private static final QueryStudentProfileService service = new QueryStudentProfileService(studentRepository, tagFacade);

    @Test
    void 학생_존재하지않음() {
        when(studentRepository.findById(anyString()))
                .thenReturn(Optional.empty());

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

        when(studentRepository.findById(email))
                .thenReturn(Optional.of(student));
        when(tagFacade.queryRegisteredTagValue(student))
                .thenReturn(List.of());

        service.execute(email);
    }

}