package kr.hs.entrydsm.raisepercent.domain.document.presentation;

import javax.validation.Valid;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.request.CreateDocumentRequest;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response.CreateDocumentResponse;
import kr.hs.entrydsm.raisepercent.domain.document.service.CreateDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreateDocumentResponse createDocument(@RequestBody @Valid CreateDocumentRequest request) {
		return createDocumentService.execute(request);
	}
}
