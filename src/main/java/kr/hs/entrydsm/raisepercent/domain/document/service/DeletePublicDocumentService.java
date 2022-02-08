package kr.hs.entrydsm.raisepercent.domain.document.service;

import java.util.UUID;
import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.PublicDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.facade.DocumentFacade;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeletePublicDocumentService {

	private final PublicDocumentRepository publicDocumentRepository;
	private final DocumentFacade documentFacade;
	private final StudentFacade studentFacade;

	public void execute(String documentId) {
		final UUID documentUUID = UUIDUtil.convertToUUID(documentId);
		Document document = documentFacade.getDocument(documentUUID);

		if (document.getStudent() != studentFacade.getCurrentStudent()) {
			throw InvalidRoleException.EXCEPTION;
		}

		publicDocumentRepository.deleteByIdDocumentId(documentUUID);
	}
}
