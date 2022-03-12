package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.hr.domain.Hr;
import kr.hs.entrydsm.raisepercent.domain.hr.domain.repositories.HrRepository;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.repositories.TeacherRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.RefreshToken;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.RefreshTokenRepository;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidTokenException;
import kr.hs.entrydsm.raisepercent.global.exception.UserNotFoundException;
import kr.hs.entrydsm.raisepercent.global.properties.JwtProperties;
import kr.hs.entrydsm.raisepercent.global.security.jwt.JwtTokenProvider;
import kr.hs.entrydsm.raisepercent.global.security.jwt.dto.TokenResponse;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserRefreshTokenServiceTest {

    private static final RefreshTokenRepository refreshTokenRepository = mock(RefreshTokenRepository.class);

    private static final JwtTokenProvider jwtTokenProvider = mock(JwtTokenProvider.class);

    private static final StudentRepository studentRepository = mock(StudentRepository.class);

    private static final TeacherRepository teacherRepository = mock(TeacherRepository.class);

    private static final HrRepository hrRepository = mock(HrRepository.class);

    private static final JwtProperties jwtProperties = mock(JwtProperties.class);

    private static final UserRefreshTokenService service = new UserRefreshTokenService(
            refreshTokenRepository,
            jwtTokenProvider,
            studentRepository,
            teacherRepository,
            hrRepository,
            jwtProperties
    );

    @Test
    void 학생_토큰_재발급() {
        String email = "student@test.com";
        String refreshToken = "refreshToken";
        String newAccessToken = "newAccessToken";
        String newRefreshToken = "newRefreshToken";

        User user = User.builder()
                .email(email)
                .build();

        Student student = Student.builder()
                .user(user)
                .build();

        RefreshToken getRefreshToken = RefreshToken.builder()
                .email(email)
                .token(refreshToken)
                .build();

        when(jwtTokenProvider.isRefreshToken(refreshToken))
                .thenReturn(true);

        when(studentRepository.findById(email))
                .thenReturn(Optional.of(student));

        when(refreshTokenRepository.findByToken(refreshToken))
                .thenReturn(Optional.of(getRefreshToken));

        when(jwtTokenProvider.generateAccessToken(any(), any()))
                .thenReturn(newAccessToken);

        when(jwtTokenProvider.generateRefreshToken(any(), any()))
                .thenReturn(newRefreshToken);

        TokenResponse response = service.execute(refreshToken);

        assertThat(response.getAccessToken()).isEqualTo(newAccessToken);
        assertThat(response.getRefreshToken()).isEqualTo(newRefreshToken);
    }

    @Test
    void 선생님_토큰_재발급() {
        String email = "teacher@test.com";
        String refreshToken = "refreshToken";
        String newAccessToken = "newAccessToken";
        String newRefreshToken = "newRefreshToken";

        User user = User.builder()
                .email(email)
                .build();

        Teacher teacher = Teacher.builder()
                .user(user)
                .build();

        RefreshToken getRefreshToken = RefreshToken.builder()
                .email(email)
                .token(refreshToken)
                .build();

        when(jwtTokenProvider.isRefreshToken(refreshToken))
                .thenReturn(true);

        when(teacherRepository.findById(email))
                .thenReturn(Optional.of(teacher));

        when(refreshTokenRepository.findByToken(refreshToken))
                .thenReturn(Optional.of(getRefreshToken));

        when(jwtTokenProvider.generateAccessToken(any(), any()))
                .thenReturn(newAccessToken);

        when(jwtTokenProvider.generateRefreshToken(any(), any()))
                .thenReturn(newRefreshToken);

        TokenResponse response = service.execute(refreshToken);

        assertThat(response.getAccessToken()).isEqualTo(newAccessToken);
        assertThat(response.getRefreshToken()).isEqualTo(newRefreshToken);
    }

    @Test
    void 회사_토큰_재발급() {
        String email = "student@test.com";
        String refreshToken = "refreshToken";
        String newAccessToken = "newAccessToken";
        String newRefreshToken = "newRefreshToken";

        User user = User.builder()
                .email(email)
                .build();

        Hr hr = Hr.builder()
                .user(user)
                .build();

        RefreshToken getRefreshToken = RefreshToken.builder()
                .email(email)
                .token(refreshToken)
                .build();

        when(jwtTokenProvider.isRefreshToken(refreshToken))
                .thenReturn(true);

        when(hrRepository.findById(email))
                .thenReturn(Optional.of(hr));

        when(refreshTokenRepository.findByToken(refreshToken))
                .thenReturn(Optional.of(getRefreshToken));

        when(jwtTokenProvider.generateAccessToken(any(), any()))
                .thenReturn(newAccessToken);

        when(jwtTokenProvider.generateRefreshToken(any(), any()))
                .thenReturn(newRefreshToken);

        TokenResponse response = service.execute(refreshToken);

        assertThat(response.getAccessToken()).isEqualTo(newAccessToken);
        assertThat(response.getRefreshToken()).isEqualTo(newRefreshToken);
    }

    @Test
    void 토큰_예외() {
        String token = "refreshToken";

        when(refreshTokenRepository.findByToken(token))
                .thenReturn(Optional.empty());

        assertThrows(InvalidTokenException.class, () -> service.execute(token));
    }

    @Test
    void 유저_예외() {
        String email = "test@test.com";
        String token = "refreshToken";

        RefreshToken getRefreshToken = RefreshToken.builder()
                .email(email)
                .token(token)
                .build();

        when(refreshTokenRepository.findByToken(token))
                .thenReturn(Optional.of(getRefreshToken));

        when(studentRepository.findById(email))
                .thenReturn(Optional.empty());

        when(teacherRepository.findById(email))
                .thenReturn(Optional.empty());

        when(hrRepository.findById(email))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.execute(token));
    }


    @Test
    void 토큰_타입_예외() {
        String token = "refreshToken";

        when(jwtTokenProvider.isRefreshToken(token))
                .thenReturn(false);

        assertThrows(InvalidTokenException.class, () -> service.execute(token));
    }

}
