package kr.hs.entrydsm.raisepercent.domain.document.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.PublicDocument;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.PublicDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.facade.DocumentFacade;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response.QueryDocumentDetailsElement;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response.QueryDocumentDetailsResponse;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryPublicDocumentDetailsService {

	private final PublicDocumentRepository publicDocumentRepository;
	private final DocumentFacade documentFacade;
	private final AuthFacade authFacade;
	private final StudentFacade studentFacade;

	@Transactional(readOnly = true)
	public QueryDocumentDetailsResponse execute(String documentId) {
		final UUID documentUUID = UUIDUtil.convertToUUID(documentId);
		Document document = documentFacade.getDocument(documentUUID);

		if (authFacade.getCurrentDetails().getRole().equals(Type.STUDENT)) {
			if (document.getStudent() != studentFacade.getCurrentStudent()) {
				throw InvalidRoleException.EXCEPTION;
			}
		}

		List<PublicDocument> publicDocuments = publicDocumentRepository.findByIdDocumentId(documentUUID);
		documentFacade.checkIsEmpty(publicDocuments);

		List<QueryDocumentDetailsElement> pages = publicDocuments.stream()
			.map(
				doc -> QueryDocumentDetailsElement.builder()
				.content(doc.getContent())
				.page(doc.getId().getPage())
				.build()
			).collect(Collectors.toList());

		return new QueryDocumentDetailsResponse(pages);
	}
}
