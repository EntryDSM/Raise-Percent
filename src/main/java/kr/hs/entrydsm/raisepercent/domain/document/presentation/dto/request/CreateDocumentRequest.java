package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.request;

import kr.hs.entrydsm.raisepercent.domain.document.domain.types.DocumentType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateDocumentRequest {

	private DocumentType documentType;
}
