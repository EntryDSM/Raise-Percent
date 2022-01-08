package kr.hs.entrydsm.raisepercent.domain.student.facade;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.global.exception.StudentNotFoundException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentFacadeTest {

	private static final AuthFacade authFacade = mock(AuthFacade.class);

	private static final StudentRepository studentRepository = mock(StudentRepository.class);

	private static final StudentFacade studentFacade = new StudentFacade(authFacade, studentRepository);

	@Test
	void 학생_인증객체_가져오기() {
		final String username = "test@dsm.hs.kr";
		Student student = Student.builder().build();

		when(authFacade.getCurrentDetails())
			.thenReturn(new AuthDetails(username, Type.TEACHER));
		when(studentRepository.findById(any()))
			.thenReturn(Optional.ofNullable(student));

		assertEquals(student, studentFacade.getCurrentStudent());
	}

	@Test
	void 학생_이메일로_가져오기() {
		Student student = Student.builder().build();

		when(studentRepository.findById(any()))
				.thenReturn(Optional.of(student));

		assertEquals(student, studentFacade.getStudent("test@gmail.com"));
	}

	@Test
	void 학생_이메일_예외() {
		when(studentRepository.findById(any()))
				.thenReturn(Optional.empty());

		assertThrows(StudentNotFoundException.class, () -> studentFacade.getStudent("test@gmail.com"));
	}

}
