package kr.hs.entrydsm.raisepercent.domain.document.service;

import java.util.UUID;
import java.util.stream.Collectors;
import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.LocalDocument;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.LocalDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.facade.DocumentFacade;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.request.UpdateDocumentRequest;
import kr.hs.entrydsm.raisepercent.global.entity.DocumentContentId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateDocumentService {

	private final LocalDocumentRepository localDocumentRepository;
	private final DocumentFacade documentFacade;


	public void execute(UUID localId, UpdateDocumentRequest updateDocumentRequest) {

		Document document = documentFacade.getDocument(localId);

		localDocumentRepository.deleteByDocumentIdByQuery(localId);

		localDocumentRepository.saveAll(
			updateDocumentRequest.getPages().stream()
				.map(pageRequest -> LocalDocument.builder()
				.id(DocumentContentId.builder()
					.document(document)
					.page(pageRequest.getPage())
					.build())
				.content(pageRequest.getContent())
				.build())
				.collect(Collectors.toList())
		);
	}
}
