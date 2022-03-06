package kr.hs.entrydsm.raisepercent.domain.student.domain;

import kr.hs.entrydsm.raisepercent.domain.student.domain.types.Position;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class StudentTest {

    private final User user = User.builder().build();

    private final String number = "2114";

    private final String year = "2020";

    private final Student student = Student.builder()
            .user(user)
            .number(number)
            .year(year)
            .build();

    @Test
    void 학생_객체_생성() {
        Student student = new Student();
        assertNull(student.getEmail());
        assertNull(student.getUser());
        assertNull(student.getNumber());
        assertNull(student.getYear());
        assertNull(student.getPosition());
    }

    @Test
    void 학생_유저_가져오기() {
        assertThat(user).isEqualTo(student.getUser());
    }

    @Test
    void 학생_학번_가져오기() {
        assertThat(number).isEqualTo(student.getNumber());
    }

    @Test
    void 학생_입학년도_가져오기() {
        assertThat(year).isEqualTo(student.getYear());
    }

    @Test
    void 학생_분야_수정() {
        Position position = Position.BACKEND;
        student.updatePosition(position);
        assertThat(position).isEqualTo(student.getPosition());
    }

    @Test
    void 학생_권한_변환() {
        assertThat(Type.STUDENT).isEqualTo(student.queryType());
    }

}