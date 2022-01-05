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

	private final StudentRepository studentRepository;
	private final AuthFacade authFacade;

	public Student getCurrentStudent() {
		return studentRepository.findById(authFacade.getCurrentDetails().getUsername())
			.orElseThrow(() -> StudentNotFoundException.EXCEPTION);
	}
}
