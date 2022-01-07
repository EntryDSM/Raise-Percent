package kr.hs.entrydsm.raisepercent.domain.student.facade;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.global.exception.StudentNotFoundException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StudentFacade {

	private final AuthFacade authFacade;
	private final StudentRepository studentRepository;

	public Student getCurrentStudent() {
		System.out.println(authFacade.getCurrentDetails());
		return getStudent(authFacade.getCurrentDetails().getUsername());
	}

	public Student getStudent(String email) {
		return studentRepository.findById(email)
				.orElseThrow(() -> StudentNotFoundException.EXCEPTION);
	}

}
