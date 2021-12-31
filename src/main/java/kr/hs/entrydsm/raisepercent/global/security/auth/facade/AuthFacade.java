package kr.hs.entrydsm.raisepercent.global.security.auth.facade;

import kr.hs.entrydsm.raisepercent.domain.hr.domain.repositories.HrRepository;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.repositories.TeacherRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.global.entity.Person;
import kr.hs.entrydsm.raisepercent.global.exception.HrNotFoundException;
import kr.hs.entrydsm.raisepercent.global.exception.StudentNotFoundException;
import kr.hs.entrydsm.raisepercent.global.exception.TeacherNotFoundException;
import kr.hs.entrydsm.raisepercent.global.exception.UserNotFoundException;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthFacade {

    private final UserRepository userRepository;

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    private final HrRepository hrRepository;

    public Type queryUserRole(String email, String type) {
        return queryPerson(email, type).queryType();
    }

    private Person queryPerson(String email, String type) {
        switch (type) {
            case "student":
                return studentRepository.findById(email)
                        .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
            case "teacher":
                return teacherRepository.findById(email)
                        .orElseThrow(() -> TeacherNotFoundException.EXCEPTION);
            case "hr":
                return hrRepository.findById(email)
                        .orElseThrow(() -> HrNotFoundException.EXCEPTION);
            default:
                return userRepository.findById(email)
                        .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        }
    }

}
