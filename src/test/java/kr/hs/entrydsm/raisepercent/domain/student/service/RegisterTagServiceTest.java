package kr.hs.entrydsm.raisepercent.domain.student.service;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.request.RegisterTagRequest;
import kr.hs.entrydsm.raisepercent.domain.tag.facade.TagFacade;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegisterTagServiceTest {

    private static final StudentFacade studentFacade = mock(StudentFacade.class);

    private static final TagFacade tagFacade = mock(TagFacade.class);

    private static final RegisterTagService service = new RegisterTagService(studentFacade, tagFacade);

    @Test
    void 태그_등록() {
        RegisterTagRequest request = new RegisterTagRequest();
        Student student = Student.builder().build();

        when(studentFacade.getCurrentStudent())
                .thenReturn(student);

        service.execute(request);
    }

}