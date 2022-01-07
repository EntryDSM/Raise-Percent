package kr.hs.entrydsm.raisepercent.domain.code.service;

import kr.hs.entrydsm.raisepercent.domain.code.domain.Code;
import kr.hs.entrydsm.raisepercent.domain.code.domain.repositories.CodeRepository;
import kr.hs.entrydsm.raisepercent.domain.code.facade.CodeAlreadyExistsException;
import kr.hs.entrydsm.raisepercent.domain.code.facade.CodeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CodeIssueService {

    private final CodeFacade codeFacade;
    private final CodeRepository codeRepository;

    private static final String id = "TEACHERVERIFICATIONCODE";

    @Transactional
    public String execute() {
        String code = codeFacade.getCode();

        if(codeRepository.findById(id).isPresent()) {
            throw CodeAlreadyExistsException.EXCEPTION;
        }

        codeRepository.save(Code.builder()
                .id(id)
                .value(code)
                .build());

        return code;
    }

}
