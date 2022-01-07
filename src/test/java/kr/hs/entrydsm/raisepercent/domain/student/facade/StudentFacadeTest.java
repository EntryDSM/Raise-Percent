package kr.hs.entrydsm.raisepercent.domain.student.facade;

import java.util.Optional;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentFacadeTest {

	@Mock
	private static AuthFacade authFacade;

	@Mock
	private static StudentRepository studentRepository;

	@InjectMocks
	private static StudentFacade studentFacade;

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
}
