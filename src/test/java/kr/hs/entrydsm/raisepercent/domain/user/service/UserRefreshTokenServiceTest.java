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
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidTokenException;
import kr.hs.entrydsm.raisepercent.global.exception.UserNotFoundException;
import kr.hs.entrydsm.raisepercent.global.properties.JwtProperties;
import kr.hs.entrydsm.raisepercent.global.security.jwt.JwtTokenProvider;
import kr.hs.entrydsm.raisepercent.global.security.jwt.dto.TokenResponse;
import kr.hs.entrydsm.raisepercent.global.security.jwt.type.TokenRole;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserRefreshTokenServiceTest {

    private static final RefreshTokenRepository refreshTokenRepository = mock(RefreshTokenRepository.class);

    private static final JwtTokenProvider jwtTokenProvider = mock(JwtTokenProvider.class);

    private static final StudentRepository studentRepository = mock(StudentRepository.class);

    private static final TeacherRepository teacherRepository = mock(TeacherRepository.class);

    private static final HrRepository hrRepository = mock(HrRepository.class);

    private static final UserRepository userRepository = mock(UserRepository.class);

    private static final JwtProperties jwtProperties = mock(JwtProperties.class);

    private static final UserRefreshTokenService service = new UserRefreshTokenService(
            refreshTokenRepository,
            jwtTokenProvider,
            studentRepository,
            teacherRepository,
            hrRepository,
            userRepository,
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

        given(jwtTokenProvider.isRefreshToken(refreshToken))
                .willReturn(true);

        given(refreshTokenRepository.findByToken(refreshToken))
                .willReturn(Optional.of(getRefreshToken));

        given(jwtTokenProvider.getRole(refreshToken))
                .willReturn(TokenRole.STUDENT);

        given(studentRepository.findById(email))
                .willReturn(Optional.of(student));

        given(jwtTokenProvider.generateAccessToken(any(), any()))
                .willReturn(newAccessToken);

        given(jwtTokenProvider.generateRefreshToken(any(), any()))
                .willReturn(newRefreshToken);

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

        given(jwtTokenProvider.isRefreshToken(refreshToken))
                .willReturn(true);

        given(refreshTokenRepository.findByToken(refreshToken))
                .willReturn(Optional.of(getRefreshToken));

        given(jwtTokenProvider.getRole(refreshToken))
                .willReturn(TokenRole.TEACHER);

        given(teacherRepository.findById(email))
                .willReturn(Optional.of(teacher));

        given(jwtTokenProvider.generateAccessToken(any(), any()))
                .willReturn(newAccessToken);

        given(jwtTokenProvider.generateRefreshToken(any(), any()))
                .willReturn(newRefreshToken);

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

        given(jwtTokenProvider.isRefreshToken(refreshToken))
                .willReturn(true);

        given(refreshTokenRepository.findByToken(refreshToken))
                .willReturn(Optional.of(getRefreshToken));

        given(jwtTokenProvider.getRole(refreshToken))
                .willReturn(TokenRole.HR_MANAGER);

        given(hrRepository.findById(email))
                .willReturn(Optional.of(hr));

        given(jwtTokenProvider.generateAccessToken(any(), any()))
                .willReturn(newAccessToken);

        given(jwtTokenProvider.generateRefreshToken(any(), any()))
                .willReturn(newRefreshToken);

        TokenResponse response = service.execute(refreshToken);

        assertThat(response.getAccessToken()).isEqualTo(newAccessToken);
        assertThat(response.getRefreshToken()).isEqualTo(newRefreshToken);
    }

    @Test
    void 유저_토큰_재발급() {
        String email = "user@test.com";
        String refreshToken = "refreshToken";
        String newAccessToken = "newAccessToken";
        String newRefreshToken = "newRefreshToken";

        User user = User.builder()
                .email(email)
                .build();

        RefreshToken getRefreshToken = RefreshToken.builder()
                .email(email)
                .token(refreshToken)
                .build();

        given(jwtTokenProvider.isRefreshToken(refreshToken))
                .willReturn(true);

        given(refreshTokenRepository.findByToken(refreshToken))
                .willReturn(Optional.of(getRefreshToken));

        given(userRepository.findById(email))
                .willReturn(Optional.of(user));

        given(jwtTokenProvider.getRole(refreshToken))
                .willReturn(TokenRole.USER);

        given(jwtTokenProvider.generateAccessToken(any(), any()))
                .willReturn(newAccessToken);

        given(jwtTokenProvider.generateRefreshToken(any(), any()))
                .willReturn(newRefreshToken);

        TokenResponse response = service.execute(refreshToken);

        assertThat(response.getAccessToken()).isEqualTo(newAccessToken);
        assertThat(response.getRefreshToken()).isEqualTo(newRefreshToken);
    }

    @Test
    void 토큰_타입_예외() {
        String token = "refreshToken";

        given(jwtTokenProvider.isRefreshToken(token))
                .willReturn(false);

        assertThrows(InvalidTokenException.class, () -> service.execute(token));
    }

    @Test
    void 토큰_예외() {
        String token = "refreshToken";

        when(refreshTokenRepository.findByToken(token))
                .thenReturn(Optional.empty());

        assertThrows(InvalidTokenException.class, () -> service.execute(token));
    }

    @Test
    void 학생_예외() {
        String email = "test@test.com";
        String token = "refreshToken";

        RefreshToken getRefreshToken = RefreshToken.builder()
                .email(email)
                .token(token)
                .build();

        given(jwtTokenProvider.isRefreshToken(token))
                .willReturn(true);

        given(refreshTokenRepository.findByToken(token))
                .willReturn(Optional.of(getRefreshToken));

        given(jwtTokenProvider.getRole(token))
                .willReturn(TokenRole.STUDENT);

        given(studentRepository.findById(email))
                .willReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.execute(token));
    }

    @Test
    void 선생님_예외() {
        String email = "test@test.com";
        String token = "refreshToken";

        RefreshToken getRefreshToken = RefreshToken.builder()
                .email(email)
                .token(token)
                .build();

        given(jwtTokenProvider.isRefreshToken(token))
                .willReturn(true);

        given(refreshTokenRepository.findByToken(token))
                .willReturn(Optional.of(getRefreshToken));

        given(jwtTokenProvider.getRole(token))
                .willReturn(TokenRole.TEACHER);

        given(teacherRepository.findById(email))
                .willReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.execute(token));
    }

    @Test
    void 회사_예외() {
        String email = "test@test.com";
        String token = "refreshToken";

        RefreshToken getRefreshToken = RefreshToken.builder()
                .email(email)
                .token(token)
                .build();

        given(jwtTokenProvider.isRefreshToken(token))
                .willReturn(true);

        given(refreshTokenRepository.findByToken(token))
                .willReturn(Optional.of(getRefreshToken));

        given(jwtTokenProvider.getRole(token))
                .willReturn(TokenRole.HR_MANAGER);

        given(hrRepository.findById(email))
                .willReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.execute(token));
    }

    @Test
    void 유저_예외() {
        String email = "test@test.com";
        String token = "refreshToken";

        RefreshToken getRefreshToken = RefreshToken.builder()
                .email(email)
                .token(token)
                .build();

        given(jwtTokenProvider.isRefreshToken(token))
                .willReturn(true);

        given(refreshTokenRepository.findByToken(token))
                .willReturn(Optional.of(getRefreshToken));

        given(jwtTokenProvider.getRole(token))
                .willReturn(TokenRole.USER);

        given(userRepository.findById(email))
                .willReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.execute(token));
    }



}
