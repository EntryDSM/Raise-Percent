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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthFacadeTest {

	private static final Authentication authentication = mock(Authentication.class);

	private static final AuthDetails authDetails = mock(AuthDetails.class);

	private static final UserRepository userRepository = mock(UserRepository.class);

	private static final StudentRepository studentRepository = mock(StudentRepository.class);

	private static final TeacherRepository teacherRepository = mock(TeacherRepository.class);

	private static final HrRepository hrRepository = mock(HrRepository.class);

	private static final AuthFacade authFacade = new AuthFacade(userRepository, studentRepository, teacherRepository, hrRepository);

	@BeforeEach
	void securityContextConfig() {
		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

	@Test
	void 학생_역할() {
		Student student = Student.builder()
			.build();

		when(studentRepository.findById(anyString()))
			.thenReturn(Optional.of(student));

		Type type = authFacade.queryUserRole("test@gamil.com", TokenRole.STUDENT);

		assertEquals(Type.STUDENT, type);
	}

	@Test
	void 선생님_역할() {
		Teacher teacher = Teacher.builder()
			.role(Role.ROOT)
			.build();

		when(teacherRepository.findById(anyString()))
			.thenReturn(Optional.of(teacher));

		Type type = authFacade.queryUserRole("test@gmail.com", TokenRole.TEACHER);

		assertEquals(Type.ROOT, type);
	}

	@Test
	void 인사담당자_역할() {
		Company company = Company.builder()
			.rankValue(Rank.SENIOR)
			.build();
		Hr hr = Hr.builder()
			.company(company)
			.build();

		when(hrRepository.findById(anyString()))
			.thenReturn(Optional.of(hr));

		Type type = authFacade.queryUserRole("test@gmail.com", TokenRole.HR_MANAGER);

		assertEquals(Type.SENIOR, type);
	}

	@Test
	void 유저_역할() {
		User user = User.builder()
			.build();

		when(userRepository.findById(anyString()))
			.thenReturn(Optional.of(user));

		Type type = authFacade.queryUserRole("test@gmail.com", TokenRole.USER);

		assertNull(type);
	}

	@Test
	void 현재_인증객체_가져오기() {

		when(authentication.getPrincipal())
			.thenReturn(authDetails);

		assertEquals(authDetails, authFacade.getCurrentDetails());
	}

	@Test
	void 인증객체_존재하지않음() {

		when(authentication.getPrincipal())
			.thenReturn(null);

		assertThrows(CredentialsNotFoundException.class, authFacade::getCurrentDetails);
	}
}
