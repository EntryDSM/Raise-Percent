package kr.hs.entrydsm.raisepercent.domain.code.service;

import kr.hs.entrydsm.raisepercent.domain.code.domain.Code;
import kr.hs.entrydsm.raisepercent.domain.code.domain.repositories.CodeRepository;
import kr.hs.entrydsm.raisepercent.domain.code.exception.CodeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryCodeService {

    private final CodeRepository codeRepository;

    @Value("${code.value}")
    private String id;

    @Transactional(readOnly = true)
    public String execute() {
        return codeRepository.findById(id)
                .map(Code::getValue)
                .orElseThrow(() -> CodeNotFoundException.EXCEPTION);
    }

}
