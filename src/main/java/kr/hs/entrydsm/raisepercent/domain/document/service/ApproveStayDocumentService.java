package kr.hs.entrydsm.raisepercent.domain.document.service;

import kr.hs.entrydsm.raisepercent.domain.document.domain.PublicDocument;
import kr.hs.entrydsm.raisepercent.domain.document.domain.StayDocument;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.PublicDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.StayDocumentRepository;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ApproveStayDocumentService {

    private final StayDocumentRepository stayDocumentRepository;
    private final PublicDocumentRepository publicDocumentRepository;

    @Transactional
    public void execute(String documentId) {
        UUID uuid = UUIDUtil.convertToUUID(documentId);

        List<StayDocument> stayDocument = stayDocumentRepository.findByIdDocumentId(uuid)
                .stream()
                .peek(stayDoc -> {
                    PublicDocument publicDocument = PublicDocument.builder()
                            .id(stayDoc.getId())
                            .content(stayDoc.getContent())
                            .build();

                    publicDocumentRepository.save(publicDocument);
                })
                .collect(Collectors.toList());

        stayDocumentRepository.deleteAll(stayDocument);
    }

}
