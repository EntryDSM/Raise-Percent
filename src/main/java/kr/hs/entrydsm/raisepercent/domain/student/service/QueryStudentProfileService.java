package kr.hs.entrydsm.raisepercent.domain.student.service;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response.ProfileResponse;
import kr.hs.entrydsm.raisepercent.domain.tag.facade.TagFacade;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryStudentProfileService {

    private final StudentFacade studentFacade;
    private final TagFacade tagFacade;

    public ProfileResponse execute(String email) {
        Student student = studentFacade.getStudent(email);
        User user = student.getUser();

        return new ProfileResponse(user.getName(), user.getContactEmail(), user.getContactTel(),
                student.getPosition().name(), tagFacade.queryRegisteredTagValue(student));
    }

}
