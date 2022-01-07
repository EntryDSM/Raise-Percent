package kr.hs.entrydsm.raisepercent.domain.student.service;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response.ProfileResponse;
import kr.hs.entrydsm.raisepercent.domain.tag.facade.TagFacade;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.global.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryStudentProfileService {

    private final StudentRepository studentRepository;
    private final TagFacade tagFacade;

    public ProfileResponse execute(String email) {
        Student student = studentRepository.findById(email)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
        User user = student.getUser();

        return new ProfileResponse(user.getName(), user.getContactEmail(), user.getContactTel(),
                student.getPosition(), tagFacade.queryRegisteredTagValue(student));
    }

}
