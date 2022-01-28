package kr.hs.entrydsm.raisepercent.domain.feedback.service;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.feedback.domain.Feedback;
import kr.hs.entrydsm.raisepercent.domain.feedback.facade.FeedbackFacade;
import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.response.ShowFeedbackResponse;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.auth.detail.AuthDetails;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowFeedbackServiceTest {

    private final FeedbackFacade feedbackFacade = mock(FeedbackFacade.class);

    private final AuthFacade authFacade = mock(AuthFacade.class);

    private final StudentFacade studentFacade = mock(StudentFacade.class);

    private final ShowFeedbackService service = new ShowFeedbackService(feedbackFacade, authFacade, studentFacade);

    @Test
    void 피드백_보기() {
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

        when(feedbackFacade.getFeedback(UUID.fromString(id)))
                .thenReturn(feedback);

        when(authFacade.getCurrentDetails())
                .thenReturn(new AuthDetails("email@dsm.hs.kr", Type.STUDENT));

        when(studentFacade.getCurrentStudent())
                .thenReturn(student);

        ShowFeedbackResponse response = service.execute(id);

        assertThat(response.getContent()).isEqualTo(content);
    }

    @Test
    void 피드백_보기_실패() {
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

        when(feedbackFacade.getFeedback(UUID.fromString(id)))
                .thenReturn(feedback);

        when(authFacade.getCurrentDetails())
                .thenReturn(new AuthDetails("email@dsm.hs.kr", Type.STUDENT));

        when(studentFacade.getCurrentStudent())
                .thenReturn(student2);

        assertThrows(InvalidRoleException.class, () -> service.execute(id));
    }
}
