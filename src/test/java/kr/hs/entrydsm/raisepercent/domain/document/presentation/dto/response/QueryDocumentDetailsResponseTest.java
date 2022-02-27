package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class QueryDocumentDetailsResponseTest {

	private static final QueryDocumentDetailsElement element = QueryDocumentDetailsElementTest.element;

	private static final QueryDocumentDetailsResponse response = new QueryDocumentDetailsResponse(List.of(element));

	@Test
	void 반환객체_요소_가져오기() {
		assertEquals(response.getPages(), List.of(element));
	}
}
