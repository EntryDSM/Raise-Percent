package kr.hs.entrydsm.raisepercent.global.facade;

import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import kr.hs.entrydsm.raisepercent.domain.company.domain.types.Rank;
import kr.hs.entrydsm.raisepercent.domain.hr.domain.Hr;
import kr.hs.entrydsm.raisepercent.domain.hr.domain.repositories.HrRepository;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.repositories.TeacherRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.types.Role;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.global.exception.CredentialsNotFoundException;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import kr.hs.entrydsm.raisepercent.global.security.jwt.type.TokenRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthFacadeTest {

	@Mock
	private Authentication authentication;

	@Mock
	private AuthDetails authDetails;

	@Mock
	private UserRepository userRepository;

	@Mock
	private StudentRepository studentRepository;

	@Mock
	private TeacherRepository teacherRepository;

	@Mock
	private HrRepository hrRepository;

	@InjectMocks
	private AuthFacade authFacade;

	@BeforeEach
	void securityContextConfig() {
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	@Test
	void 학생_역할() {
		//given
		Student student = Student.builder()
			.build();

		given(studentRepository.findById(anyString()))
			.willReturn(Optional.of(student));

		//when
		Type type = authFacade.queryUserRole("test@gamil.com", TokenRole.STUDENT);

		//then
		assertThat(Type.STUDENT).isEqualTo(type);
	}

	@Test
	void 선생님_역할() {
		//given
		Teacher teacher = Teacher.builder()
			.role(Role.ROOT)
			.build();

		given(teacherRepository.findById(anyString()))
			.willReturn(Optional.of(teacher));

		//when
		Type type = authFacade.queryUserRole("test@gmail.com", TokenRole.TEACHER);

		//then
		assertThat(Type.ROOT).isEqualTo(type);
	}

	@Test
	void 인사담당자_역할() {
		//given
		Company company = Company.builder()
			.rankValue(Rank.SENIOR)
			.build();
		Hr hr = Hr.builder()
			.company(company)
			.build();

		given(hrRepository.findById(anyString()))
			.willReturn(Optional.of(hr));

		//when
		Type type = authFacade.queryUserRole("test@gmail.com", TokenRole.HR_MANAGER);

		//then
		assertThat(Type.SENIOR).isEqualTo(type);
	}

	@Test
	void 유저_역할() {
		//given
		User user = User.builder()
			.build();

		given(userRepository.findById(anyString()))
			.willReturn(Optional.of(user));

		//when
		Type type = authFacade.queryUserRole("test@gmail.com", TokenRole.USER);

		//then
		assertNull(type);
	}

	@Test
	void 현재_인증객체_가져오기() {
		//given
		given(authentication.getPrincipal())
			.willReturn(authDetails);

		//when
		AuthDetails currentDetails = authFacade.getCurrentDetails();

		//then
		assertThat(authDetails).isEqualTo(currentDetails);
	}

	@Test
	void 인증객체_존재하지않음() {
		//given
		when(authentication.getPrincipal())
			.thenReturn(null);

		//when then
		assertThrows(CredentialsNotFoundException.class, authFacade::getCurrentDetails);
	}
}
