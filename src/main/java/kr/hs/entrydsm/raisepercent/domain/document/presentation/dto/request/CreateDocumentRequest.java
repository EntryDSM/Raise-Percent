package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.request;

import kr.hs.entrydsm.raisepercent.domain.document.domain.types.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateDocumentRequest {

	private Type type;
}
