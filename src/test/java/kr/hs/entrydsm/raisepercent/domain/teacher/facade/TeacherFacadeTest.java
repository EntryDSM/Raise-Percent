package kr.hs.entrydsm.raisepercent.domain.teacher.facade;

import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.types.Role;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeacherFacadeTest {

    @Test
    void 선생님_정보_가져오기() {
        String email = "test@gmail.com";

        User user = User.builder()
                .email(email)
                .build();

        Teacher teacher = Teacher.builder()
                .user(user)
                .role(Role.ROOT)
                .build();

        assertEquals(user.getContactEmail(), teacher.getUser().getContactEmail());
    }

}
