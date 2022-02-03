package kr.hs.entrydsm.raisepercent.domain.document.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.LocalDocument;
import kr.hs.entrydsm.raisepercent.domain.document.domain.StayDocument;
import kr.hs.entrydsm.raisepercent.domain.document.domain.SubmittedDocument;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.LocalDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.StayDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.SubmittedDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.facade.DocumentFacade;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.global.exception.DocumentNotFoundException;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SubmitDocumentService {

	private final LocalDocumentRepository localDocumentRepository;
	private final StayDocumentRepository stayDocumentRepository;
	private final SubmittedDocumentRepository submittedDocumentRepository;
	private final DocumentFacade documentFacade;
	private final StudentFacade studentFacade;

	public void execute(String documentId) {
		final UUID documentUUID = UUIDUtil.convertToUUID(documentId);
		Document document = documentFacade.getDocument(documentUUID);

		if (document.getStudent() != studentFacade.getCurrentStudent()) {
			throw InvalidRoleException.EXCEPTION;
		}

		List<LocalDocument> localDocument = localDocumentRepository.findByIdDocumentId(documentUUID);

		if (localDocument.isEmpty()) {
			throw DocumentNotFoundException.EXCEPTION;
		}

		stayDocumentRepository.saveAll(
			localDocument.stream()
			.map(doc -> StayDocument.builder()
					.id(doc.getId())
					.content(doc.getContent())
					.build())
			.collect(Collectors.toList())
		);

		submittedDocumentRepository.save(SubmittedDocument.builder().document(document).build());
	}
}