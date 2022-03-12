package kr.hs.entrydsm.raisepercent.domain.tag.domain;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class RegisteredTagTest {

    private final Tag tag = Tag.builder()
            .build();

    private final Student student = Student.builder()
            .build();

    private final RegisteredTag registeredTag = RegisteredTag.builder()
            .tag(tag)
            .student(student)
            .build();

    @Test
    void 등록된_태그_객체_생성() {
        RegisteredTag registeredTag = new RegisteredTag();
        assertNull(registeredTag.getId());
        assertNull(registeredTag.getTag());
        assertNull(registeredTag.getStudent());
    }

    @Test
    void 태그_가져오기() {
        assertThat(tag).isEqualTo(registeredTag.getTag());
    }

    @Test
    void 학생_가져오기() {
        assertThat(student).isEqualTo(registeredTag.getStudent());
    }

}