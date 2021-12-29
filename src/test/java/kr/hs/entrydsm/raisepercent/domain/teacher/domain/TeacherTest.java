package kr.hs.entrydsm.raisepercent.domain.teacher.domain;

import kr.hs.entrydsm.raisepercent.domain.teacher.domain.types.Role;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    private static final User user = User.builder()
            .build();

    private static final Role role = Role.DEFAULT;

    private static final Teacher teacher = Teacher.builder()
            .user(user)
            .role(role)
            .build();

    @Test
    void 선생님_객체_생성() {
        Teacher teacher = new Teacher();
        assertNull(teacher.getEmail());
        assertNull(teacher.getUser());
        assertNull(teacher.getRole());
    }

    @Test
    void 유저_가져오기() {
        assertEquals(user, teacher.getUser());
    }

    @Test
    void 권한_가져오기() {
        assertEquals(role, teacher.getRole());
    }

    @Test
    void 선생님_기본권한_수정() {
        Role role = Role.TEACHER;
        teacher.updateRole(role);
        assertEquals(role, teacher.getRole());
    }

    @Test
    void 선생님_권한_수정() {
        Role role = Role.ROOT;
        teacher.updateRole(role);
        assertEquals(role, teacher.getRole());
    }

}