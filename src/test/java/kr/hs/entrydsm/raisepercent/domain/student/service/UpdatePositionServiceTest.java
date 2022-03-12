package kr.hs.entrydsm.raisepercent.domain.student.service;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.request.UpdatePositionRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UpdatePositionServiceTest {

    @Mock
    private StudentFacade studentFacade;

    @Mock
    private UpdatePositionRequest request;

    @InjectMocks
    private UpdatePositionService service;

    @Test
    void 분야_수정() {
        Student student = Student.builder().build();

        given(studentFacade.getCurrentStudent())
                .willReturn(student);
        given(request.getPositionName())
                .willReturn("BACKEND");

        service.execute(request);
    }

}