package kr.hs.entrydsm.raisepercent.domain.teacher.facade;

import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.types.Role;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TeacherFacadeTest {

    private static final Authentication authentication = mock(Authentication.class);

    @BeforeEach
    void securityContextConfig() {
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

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
