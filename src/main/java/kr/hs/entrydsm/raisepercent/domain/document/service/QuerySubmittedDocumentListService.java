package kr.hs.entrydsm.raisepercent.domain.document.service;

import kr.hs.entrydsm.raisepercent.domain.document.facade.DocumentFacade;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response.SubmittedDocumentListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QuerySubmittedDocumentListService {

    private final DocumentFacade documentFacade;

    @Transactional(readOnly = true)
    public SubmittedDocumentListResponse execute() {
        return new SubmittedDocumentListResponse(documentFacade.querySubmittedDocumentList());
    }

}
