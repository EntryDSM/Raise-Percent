package kr.hs.entrydsm.raisepercent.domain.document.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.PublicDocument;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.PublicDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.facade.DocumentFacade;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response.QueryDocumentDetailsElement;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response.QueryDocumentDetailsResponse;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.global.entity.DocumentContentId;
import kr.hs.entrydsm.raisepercent.global.exception.DocumentNotFoundException;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class QueryPublicDocumentDetailsServiceTest {

	@Mock
	private PublicDocumentRepository publicDocumentRepository;

	@Mock
	private DocumentFacade documentFacade;

	@Mock
	private AuthFacade authFacade;

	@Mock
	private StudentFacade studentFacade;

	@InjectMocks
	private QueryPublicDocumentDetailsService service;

	@Mock
	private AuthDetails authDetails;

	private final Document document = UpdateDocumentServiceTest.document;

	private final Student student = UpdateDocumentServiceTest.student;

	private final UUID uuid = UUID.randomUUID();

	private final Integer page = 1;

	private final String content = "test";

	private final PublicDocument publicDocument = PublicDocument.builder()
		.id(DocumentContentId.builder()
			.page(page)
			.document(document)
			.build())
		.content(content)
		.build();

	@Test
	void 공개_문서_상세() {
		//given
		given(documentFacade.getDocument(uuid))
			.willReturn(document);
		given(authFacade.getCurrentDetails())
			.willReturn(authDetails);
		given(authFacade.getCurrentDetails().getRole())
			.willReturn(Type.ROOT);
		given(publicDocumentRepository.findByIdDocumentId(uuid))
			.willReturn(List.of(publicDocument));

		QueryDocumentDetailsElement element = QueryDocumentDetailsElement.builder()
			.page(page)
			.content(content)
			.build();

		//when
		QueryDocumentDetailsResponse response = service.execute(uuid.toString());

		//then
		assertThat(response).usingRecursiveComparison().isEqualTo(new QueryDocumentDetailsResponse(List.of(element)));
	}

	@Test
	void 권한없는_학생() {
		//given
		Student student = Student.builder().build();

		given(documentFacade.getDocument(uuid))
			.willReturn(document);
		given(authFacade.getCurrentDetails())
			.willReturn(authDetails);
		given(authFacade.getCurrentDetails().getRole())
			.willReturn(Type.STUDENT);
		given(studentFacade.getCurrentStudent())
			.willReturn(student);

		//when then
		assertThrows(InvalidRoleException.class, () -> service.execute(uuid.toString()));
	}

	@Test
	void 권한있는_학생() {
		//given
		given(documentFacade.getDocument(uuid))
			.willReturn(document);
		given(authFacade.getCurrentDetails())
			.willReturn(authDetails);
		given(authFacade.getCurrentDetails().getRole())
			.willReturn(Type.STUDENT);
		given(studentFacade.getCurrentStudent())
			.willReturn(student);

		//when then
		assertDoesNotThrow(() -> service.execute(uuid.toString()));
	}

	@Test
	void 빈_문서() {
		//given
		List<PublicDocument> publicDocumentList = new ArrayList<>();

		given(documentFacade.getDocument(uuid))
			.willReturn(document);
		given(authFacade.getCurrentDetails())
			.willReturn(authDetails);
		given(authFacade.getCurrentDetails().getRole())
			.willReturn(Type.ROOT);
		given(publicDocumentRepository.findByIdDocumentId(uuid))
			.willReturn(publicDocumentList);

		//when then
		willThrow(DocumentNotFoundException.class).given(documentFacade).assertNotEmpty(publicDocumentList);
	}
}
