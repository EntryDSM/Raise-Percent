package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QueryDocumentDetailsResponse {

	private final List<QueryDocumentDetailsElement> pages;

}
