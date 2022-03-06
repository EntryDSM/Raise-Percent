package kr.hs.entrydsm.raisepercent.domain.notice.domain;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.types.Scope;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NoticeTest {

    private final String title = "Test title";

    private final String content = "Test content";

    private final Scope scope = Scope.ALL;

    private final Teacher teacher = Teacher.builder()
            .build();

    private final Notice notice = Notice.builder()
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
        assertThat(title).isEqualTo(notice.getTitle());
    }

    @Test
    @Order(2)
    void 공지_내용_가져오기() {
        assertThat(content).isEqualTo(notice.getContent());
    }

    @Test
    void 공지_범위_가져오기() {
        assertThat(scope).isEqualTo(notice.getScope());
    }

    @Test
    void 공지_선생님_가져오기() {
        assertThat(teacher).isEqualTo(notice.getTeacher());
    }

    @Test
    void 공지_제목_수정() {
        String title = "Updated title";
        notice.updateTitle(title);
        assertThat(title).isEqualTo(notice.getTitle());
    }

    @Test
    void 공지_내용_수정() {
        String content = "Updated content";
        notice.updateContent(content);
        assertThat(content).isEqualTo(notice.getContent());
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
        assertThat(scope).isEqualTo(notice.getScope());
    }

}