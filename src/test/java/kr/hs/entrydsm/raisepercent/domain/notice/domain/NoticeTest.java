package kr.hs.entrydsm.raisepercent.domain.notice.domain;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.types.Scope;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NoticeTest {

    private static final String title = "Test title";

    private static final String content = "Test content";

    private static final Scope scope = Scope.ALL;

    private static final Teacher teacher = Teacher.builder()
            .build();

    private static final Notice notice = Notice.builder()
            .title(title)
            .content(content)
            .scope(scope)
            .teacher(teacher)
            .build();

    @Test
    void 공지_객체_생성() {
        Notice notice = new Notice();
        assertNull(notice.getId());
        assertNull(notice.getTitle());
        assertNull(notice.getContent());
        assertNull(notice.getScope());
        assertNull(notice.getTeacher());
        assertNull(notice.getCreatedAt());
    }

    @Test
    @Order(1)
    void 공지_제목_가져오기() {
        assertEquals(title, notice.getTitle());
    }

    @Test
    @Order(2)
    void 공지_내용_가져오기() {
        assertEquals(content, notice.getContent());
    }

    @Test
    void 공지_범위_가져오기() {
        assertEquals(scope, notice.getScope());
    }

    @Test
    void 공지_선생님_가져오기() {
        assertEquals(teacher, notice.getTeacher());
    }

    @Test
    void 공지_제목_수정() {
        String title = "Updated title";
        notice.updateTitle(title);
        assertEquals(title, notice.getTitle());
    }

    @Test
    void 공지_내용_수정() {
        String content = "Updated content";
        notice.updateContent(content);
        assertEquals(content, notice.getContent());
    }

    @Test
    void 공지_학생_범위() {
        Scope scope = Scope.STUDENT;
        scopeTest(scope);
    }

    @Test
    void 공지_회사_범위() {
        Scope scope = Scope.STUDENT;
        scopeTest(scope);
    }

    private void scopeTest(Scope scope) {
        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .scope(scope)
                .teacher(teacher)
                .build();
        assertEquals(scope, notice.getScope());
    }

}