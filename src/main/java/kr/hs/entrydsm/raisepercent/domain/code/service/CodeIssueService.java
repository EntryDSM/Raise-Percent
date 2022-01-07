package kr.hs.entrydsm.raisepercent.domain.code.service;

import kr.hs.entrydsm.raisepercent.domain.code.domain.Code;
import kr.hs.entrydsm.raisepercent.domain.code.domain.repositories.CodeRepository;
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
        return codeRepository.findById(id)
                .map(Code::getValue)
                .orElseGet(() -> {
                    String randomCode = codeFacade.getRandomCode();
                    codeRepository.save(Code.builder()
                            .id(id)
                            .value(randomCode)
                            .build());
                    return randomCode;
                });
    }

}
