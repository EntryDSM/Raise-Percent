package kr.hs.entrydsm.raisepercent.domain.code.service;

import kr.hs.entrydsm.raisepercent.domain.code.domain.Code;
import kr.hs.entrydsm.raisepercent.domain.code.domain.repositories.CodeRepository;
import kr.hs.entrydsm.raisepercent.domain.code.exception.CodeNotFoundException;
import kr.hs.entrydsm.raisepercent.domain.code.exception.CodeNotMatchException;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.types.Role;
import kr.hs.entrydsm.raisepercent.domain.teacher.facade.TeacherFacade;
import kr.hs.entrydsm.raisepercent.domain.teacher.presentation.dto.request.VerifyTeacherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CodeVerificationService {

    private final TeacherFacade teacherFacade;
    private final CodeRepository codeRepository;

    @Value("${code.value}")
    private String id;

    @Transactional
    public void execute(VerifyTeacherRequest request) {
        Teacher teacher = teacherFacade.getCurrentTeacher();

        String value = codeRepository.findById(id)
                .map(Code::getValue)
                .orElseThrow(() -> CodeNotFoundException.EXCEPTION);

        if (!value.equals(request.getCode())) {
            throw CodeNotMatchException.EXCEPTION;
        }

        teacher.updateRole(Role.TEACHER);

    }

}
