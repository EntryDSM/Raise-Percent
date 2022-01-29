package kr.hs.entrydsm.raisepercent.domain.document.service;

import kr.hs.entrydsm.raisepercent.domain.document.domain.PublicDocument;
import kr.hs.entrydsm.raisepercent.domain.document.domain.StayDocument;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.PublicDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.StayDocumentRepository;
import kr.hs.entrydsm.raisepercent.global.exception.DocumentNotFoundException;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApproveStayDocumentService {

    private final StayDocumentRepository stayDocumentRepository;
    private final PublicDocumentRepository publicDocumentRepository;

    @Transactional
    public void execute(String documentId) {
        List<StayDocument> stayDocumentList =
                stayDocumentRepository.findByIdDocumentId(UUIDUtil.convertToUUID(documentId));

        if (stayDocumentList.isEmpty()) {
            throw DocumentNotFoundException.EXCEPTION;
        }
        
        stayDocumentList.forEach(doc -> {
            PublicDocument publicDocument = PublicDocument.builder()
                            .id(doc.getId())
                            .content(doc.getContent())
                            .build();

            publicDocumentRepository.save(publicDocument);
        });

        stayDocumentRepository.deleteAll(stayDocumentList);
    }

}
