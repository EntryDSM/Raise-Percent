package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class QueryDocumentDetailsResponseTest {

	private final QueryDocumentDetailsElement element = QueryDocumentDetailsElementTest.element;

	private final QueryDocumentDetailsResponse response = new QueryDocumentDetailsResponse(List.of(element));

	@Test
	void 반환객체_요소_가져오기() {
		assertThat(response.getPages()).isEqualTo(List.of(element));
	}
}
