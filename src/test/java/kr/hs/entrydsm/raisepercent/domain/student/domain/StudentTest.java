package kr.hs.entrydsm.raisepercent.domain.student.domain;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private static final User user = User.builder().build();

    private static final String number = "2114";

    private static final String year = "2020";

    private static final Student student = Student.builder()
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
        assertEquals(user, student.getUser());
    }

    @Test
    void 학생_학번_가져오기() {
        assertEquals(number, student.getNumber());
    }

    @Test
    void 학생_입학년도_가져오기() {
        assertEquals(year, student.getYear());
    }

    @Test
    void 학생_분야_수정() {
        String position = "Backend";
        student.updatePosition(position);
        assertEquals(position, student.getPosition());
    }

    @Test
    void 학생_권한_변환() {
        assertEquals(Type.STUDENT, student.queryType());
    }

}