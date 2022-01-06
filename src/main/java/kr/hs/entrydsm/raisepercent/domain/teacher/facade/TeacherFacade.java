package kr.hs.entrydsm.raisepercent.domain.teacher.facade;

import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.repositories.TeacherRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.types.Role;
import kr.hs.entrydsm.raisepercent.global.exception.CredentialsNotFoundException;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import kr.hs.entrydsm.raisepercent.global.exception.UserNotFoundException;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TeacherFacade {

    private final TeacherRepository teacherRepository;

    public void verifyTeacherRole(Teacher teacher) {
        if (!teacher.getRole().equals(Role.ROOT)) {
            throw InvalidRoleException.EXCEPTION;
        }
    }

    public Teacher getTeacher() {
        return teacherRepository.findById(getCurrentDetails().getUsername())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    private AuthDetails getCurrentDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof AuthDetails)) {
            throw CredentialsNotFoundException.EXCEPTION;
        }
        return (AuthDetails) principal;
    }
}
