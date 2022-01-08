package kr.hs.entrydsm.raisepercent.domain.code.service;

import kr.hs.entrydsm.raisepercent.domain.code.domain.Code;
import kr.hs.entrydsm.raisepercent.domain.code.domain.repositories.CodeRepository;
import kr.hs.entrydsm.raisepercent.domain.code.facade.CodeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CodeReissueService {

    @Value("${code.value}")
    private static String id;

    private final CodeFacade codeFacade;
    private final CodeRepository codeRepository;

    @Transactional
    public String execute() {
        String newCode = codeFacade.getRandomCode();

        return codeRepository.findById(id)
                .map(code -> code.updateCode(newCode))
                .orElseGet(() -> {
                    codeRepository.save(Code.builder()
                            .id(id)
                            .value(newCode)
                            .build());
                    return newCode;
                });
    }

}
