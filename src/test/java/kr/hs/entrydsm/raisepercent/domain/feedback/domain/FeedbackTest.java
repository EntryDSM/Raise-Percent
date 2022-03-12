package kr.hs.entrydsm.raisepercent.domain.feedback.domain;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FeedbackTest {

    private static final String content = "Test content";

    private static final boolean isWatch = false;

    private static final Document document = Document.builder()
            .build();

    private static final Teacher teacher = Teacher.builder()
            .build();

    private static final Feedback feedback = Feedback.builder()
            .content(content)
            .isWatch(isWatch)
            .document(document)
            .teacher(teacher)
            .build();

    @Test
    void 피드백_객체_생성() {
        Feedback feedback = new Feedback();
        assertNull(feedback.getId());
        assertNull(feedback.getContent());
        assertFalse(feedback.isWatch());
        assertNull(feedback.getDocument());
        assertNull(feedback.getTeacher());
    }

    @Test
    @Order(0)
    void 피드백_내용_가져오기() {
        assertThat(content).isEqualTo(feedback.getContent());
    }

    @Test
    void 피드백_읽음여부_가져오기() {
        assertThat(isWatch).isEqualTo(feedback.isWatch());
    }

    @Test
    void 피드백_문서_가져오기() {
        assertThat(document).isEqualTo(feedback.getDocument());
    }

    @Test
    void 피드백_선생님_가져오기() {
        assertThat(teacher).isEqualTo(feedback.getTeacher());
    }

    @Test
    void 피드백_내용_수정() {
        String content = "Updated content";
        feedback.updateContent(content);
        assertThat(content).isEqualTo(feedback.getContent());
    }

}