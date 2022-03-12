package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class QueryDocumentDetailsElementTest {

	private static final String content = "test";

	private static final Integer page = 1;

	public static final QueryDocumentDetailsElement element = QueryDocumentDetailsElement.builder()
		.content(content)
		.page(page)
		.build();

	@Test
	void 반환객체_요소_가져오기() {
		assertThat(content).isEqualTo(element.getContent());
		assertThat(page).isEqualTo(element.getPage());
	}
}
