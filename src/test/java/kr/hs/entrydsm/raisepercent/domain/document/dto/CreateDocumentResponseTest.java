package kr.hs.entrydsm.raisepercent.domain.document.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response.CreateDocumentResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateDocumentResponseTest {

	@Test
	void 문서생성_반환객체_요소_가져오기() {
		final UUID uuid = UUID.randomUUID();

		CreateDocumentResponse response = new CreateDocumentResponse(uuid);

		assertThat(response.getDocumentId()).isEqualTo(uuid);
	}

}
