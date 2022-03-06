package kr.hs.entrydsm.raisepercent.domain.student.service;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.request.RegisterTagRequest;
import kr.hs.entrydsm.raisepercent.domain.tag.facade.TagFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RegisterTagServiceTest {

    @Mock
    private StudentFacade studentFacade;

    @Mock
    private TagFacade tagFacade;

    @InjectMocks
    private RegisterTagService service;

    @Test
    void 태그_등록() {
        RegisterTagRequest request = new RegisterTagRequest();
        Student student = Student.builder().build();

        given(studentFacade.getCurrentStudent())
                .willReturn(student);

        service.execute(request);
    }

}