package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateDocumentResponse {

	private final UUID documentId;
}
