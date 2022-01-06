package kr.hs.entrydsm.raisepercent.domain.teacher.facade;

import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.repositories.TeacherRepository;
import kr.hs.entrydsm.raisepercent.global.exception.CredentialsNotFoundException;
import kr.hs.entrydsm.raisepercent.global.exception.TeacherNotFoundException;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TeacherFacade {

    private final TeacherRepository teacherRepository;

    public Teacher getTeacher() {
        return teacherRepository.findById(getCurrentDetails().getUsername())
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
    }

    private AuthDetails getCurrentDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof AuthDetails)) {
            throw CredentialsNotFoundException.EXCEPTION;
        }
        return (AuthDetails) principal;
    }
}
