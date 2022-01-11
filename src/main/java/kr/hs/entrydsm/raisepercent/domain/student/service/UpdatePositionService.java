package kr.hs.entrydsm.raisepercent.domain.student.service;

import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.domain.types.Position;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.request.UpdatePositionRequest;
import kr.hs.entrydsm.raisepercent.global.util.EnumUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdatePositionService {

    private final StudentFacade studentFacade;

    @Transactional
    public void execute(UpdatePositionRequest request) {
        Student student = studentFacade.getCurrentStudent();
        student.updatePosition(EnumUtil.convertToEnum(request.getPositionName(), Position.class));
    }

}
