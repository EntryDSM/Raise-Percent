package kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response.CreateDocumentResponse;
import org.junit.jupiter.api.Test;

class CreateDocumentResponseTest {

	@Test
	void 문서생성_반환객체_요소_가져오기() {
		final UUID uuid = UUID.randomUUID();

		CreateDocumentResponse response = new CreateDocumentResponse(uuid);

		assertThat(response.getDocumentId()).isEqualTo(uuid);
	}

}
