package kr.hs.entrydsm.raisepercent.domain.student.service;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.request.UpdatePositionRequest;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UpdatePositionServiceTest {

    private static final StudentFacade studentFacade = mock(StudentFacade.class);

    private static final UpdatePositionRequest request = mock(UpdatePositionRequest.class);

    private static final UpdatePositionService service = new UpdatePositionService(studentFacade);

    @Test
    void 분야_수정() {
        Student student = Student.builder().build();

        when(studentFacade.getCurrentStudent())
                .thenReturn(student);
        when(request.getPositionName())
                .thenReturn("BACKEND");

        service.execute(request);
    }

}