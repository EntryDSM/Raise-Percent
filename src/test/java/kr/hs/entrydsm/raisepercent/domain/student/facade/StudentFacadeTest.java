package kr.hs.entrydsm.raisepercent.domain.student.facade;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.global.exception.StudentNotFoundException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StudentFacadeTest {

	@Mock
	private AuthFacade authFacade;

	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private StudentFacade studentFacade;

	@Test
	void 학생_인증객체_가져오기() {
		//given
		final String username = "test@dsm.hs.kr";
		Student student = Student.builder().build();

		given(authFacade.getCurrentDetails())
			.willReturn(new AuthDetails(username, Type.TEACHER));
		given(studentRepository.findById(any()))
			.willReturn(Optional.ofNullable(student));

		//when then
		assertEquals(student, studentFacade.getCurrentStudent());
	}

	@Test
	void 학생_이메일로_가져오기() {
		//given
		Student student = Student.builder().build();

		given(studentRepository.findById(any()))
				.willReturn(Optional.of(student));

		//when then
		assertEquals(student, studentFacade.getStudent("test@gmail.com"));
	}

	@Test
	void 학생_이메일_예외() {
		//given
		given(studentRepository.findById(any()))
				.willReturn(Optional.empty());

		//when then
		assertThrows(StudentNotFoundException.class, () -> studentFacade.getStudent("test@gmail.com"));
	}

}
