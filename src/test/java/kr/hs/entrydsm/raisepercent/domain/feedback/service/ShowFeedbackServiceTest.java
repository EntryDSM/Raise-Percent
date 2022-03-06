package kr.hs.entrydsm.raisepercent.domain.feedback.service;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.feedback.domain.Feedback;
import kr.hs.entrydsm.raisepercent.domain.feedback.facade.FeedbackFacade;
import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.response.ShowFeedbackResponse;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShowFeedbackServiceTest {

    @Mock
    private FeedbackFacade feedbackFacade;

    @Mock
    private AuthFacade authFacade;

    @Mock
    private StudentFacade studentFacade;

    @InjectMocks
    private ShowFeedbackService service;

    @Test
    void 피드백_보기() {
        //given
        String id = String.valueOf(UUID.randomUUID());
        String content = "content";

        Student student = Student.builder().build();

        Document document = Document.builder()
                .student(student)
                .build();

        Feedback feedback = Feedback.builder()
                .content(content)
                .document(document)
                .build();

        given(feedbackFacade.getFeedback(UUID.fromString(id)))
                .willReturn(feedback);

        given(authFacade.getCurrentDetails())
                .willReturn(new AuthDetails("email@dsm.hs.kr", Type.STUDENT));

        given(studentFacade.getCurrentStudent())
                .willReturn(student);

        //when
        ShowFeedbackResponse response = service.execute(id);

        //then
        assertThat(response.getContent()).isEqualTo(content);
    }

    @Test
    void 피드백_보기_실패() {
        //given
        String id = String.valueOf(UUID.randomUUID());

        Student student = Student.builder().build();
        Student student2 = Student.builder().build();

        Document document = Document.builder()
                .student(student)
                .build();

        Feedback feedback = Feedback.builder()
                .content("content")
                .document(document)
                .build();

        given(feedbackFacade.getFeedback(UUID.fromString(id)))
                .willReturn(feedback);

        given(authFacade.getCurrentDetails())
                .willReturn(new AuthDetails("email@dsm.hs.kr", Type.STUDENT));

        given(studentFacade.getCurrentStudent())
                .willReturn(student2);

        //when then
        assertThrows(InvalidRoleException.class, () -> service.execute(id));
    }
}
