package kr.hs.entrydsm.raisepercent.domain.teacher.facade;

import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.repositories.TeacherRepository;
import kr.hs.entrydsm.raisepercent.global.exception.TeacherNotFoundException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TeacherFacade {

    private final AuthFacade authFacade;
    private final TeacherRepository teacherRepository;

    public Teacher getTeacher() {
        return teacherRepository.findById(authFacade.getCurrentDetails().getUsername())
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
    }
}
