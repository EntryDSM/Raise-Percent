package kr.hs.entrydsm.raisepercent.domain.document.presentation;

import java.util.UUID;
import javax.validation.Valid;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.request.CreateDocumentRequest;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.request.UpdateDocumentRequest;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response.CreateDocumentResponse;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response.SubmittedDocumentListResponse;
import kr.hs.entrydsm.raisepercent.domain.document.service.ApproveStayDocumentService;
import kr.hs.entrydsm.raisepercent.domain.document.service.CreateDocumentService;
import kr.hs.entrydsm.raisepercent.domain.document.service.QuerySubmittedDocumentListService;
import kr.hs.entrydsm.raisepercent.domain.document.service.UpdateDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/documents")
@RestController
public class DocumentController {

	private final CreateDocumentService createDocumentService;
	private final UpdateDocumentService updateDocumentService;
	private final QuerySubmittedDocumentListService querySubmittedDocumentListService;
	private final ApproveStayDocumentService approveStayDocumentService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CreateDocumentResponse createDocument(@RequestBody @Valid CreateDocumentRequest request) {
		return createDocumentService.execute(request);
	}

	@GetMapping("/submit")
	public SubmittedDocumentListResponse querySubmittedDocumentList() {
		return querySubmittedDocumentListService.execute();
	}

	@PatchMapping("/local/{local-document-id}")
	public void updateDocument(@PathVariable("local-document-id") UUID localId,
		@RequestBody UpdateDocumentRequest updateDocumentRequest) {
		updateDocumentService.execute(localId, updateDocumentRequest);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/{submitted-document-id}")
	public void approveSubmittedDocument(@PathVariable(name = "submitted-document-id") String documentId) {
		approveStayDocumentService.execute(documentId);
	}

}
