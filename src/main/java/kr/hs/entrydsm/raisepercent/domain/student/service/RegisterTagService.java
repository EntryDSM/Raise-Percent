package kr.hs.entrydsm.raisepercent.domain.student.service;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.request.RegisterTagRequest;
import kr.hs.entrydsm.raisepercent.domain.tag.facade.TagFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterTagService {

    private final StudentFacade studentFacade;
    private final TagFacade tagFacade;

    public void execute(RegisterTagRequest request) {
        Student student = studentFacade.getCurrentStudent();

        tagFacade.registerTag(request.getTagId(), student);
    }

}
