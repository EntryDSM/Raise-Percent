package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryDocumentDetailsElement {

	private final String content;
	private final Integer page;

}
