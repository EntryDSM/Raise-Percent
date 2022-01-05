package kr.hs.entrydsm.raisepercent.domain.teacher.facade;

import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.repositories.TeacherRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.types.Role;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import kr.hs.entrydsm.raisepercent.global.exception.TeacherNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TeacherFacade {

    private final TeacherRepository teacherRepository;

    public Teacher getTeacher() {
        return teacherRepository.findById(getEmail())
                .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
    }

    public void verifyTeacherRole(Teacher teacher) {
        if(!teacher.getRole().equals(Role.ROOT)) {
            throw InvalidRoleException.EXCEPTION;
        }
    }

    private String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw TeacherNotFoundException.EXCEPTION;
        }
        return authentication.getName();
    }
}
