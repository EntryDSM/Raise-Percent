package kr.hs.entrydsm.raisepercent.domain.document.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

class QueryPublicDocumentDetailsServiceTest {

	private static final PublicDocumentRepository publicDocumentRepository = mock(PublicDocumentRepository.class);

	private static final DocumentFacade documentFacade = mock(DocumentFacade.class);

	private static final AuthFacade authFacade = mock(AuthFacade.class);

	private static final StudentFacade studentFacade = mock(StudentFacade.class);

	private static final QueryPublicDocumentDetailsService service = new QueryPublicDocumentDetailsService(publicDocumentRepository, documentFacade, authFacade, studentFacade);

	private static final AuthDetails authDetails = mock(AuthDetails.class);

	private static final Document document = UpdateDocumentServiceTest.document;

	private static final Student student = UpdateDocumentServiceTest.student;

	private static final UUID uuid = UUID.randomUUID();

	private static final Integer page = 1;

	private static final String content = "test";

	private static final PublicDocument publicDocument = PublicDocument.builder()
		.id(DocumentContentId.builder()
			.page(page)
			.document(document)
			.build())
		.content(content)
		.build();

	@Test
	void 공개_문서_상세() {

		when(documentFacade.getDocument(uuid))
			.thenReturn(document);
		when(authFacade.getCurrentDetails())
			.thenReturn(authDetails);
		when(authFacade.getCurrentDetails().getRole())
			.thenReturn(Type.ROOT);
		when(publicDocumentRepository.findByIdDocumentId(uuid))
			.thenReturn(List.of(publicDocument));

		QueryDocumentDetailsElement element = QueryDocumentDetailsElement.builder()
			.page(page)
			.content(content)
			.build();

		QueryDocumentDetailsResponse response = service.execute(uuid.toString());

		assertThat(response).usingRecursiveComparison().isEqualTo(new QueryDocumentDetailsResponse(List.of(element)));
	}

	@Test
	void 권한없는_학생() {
		Student student = Student.builder().build();

		when(documentFacade.getDocument(uuid))
			.thenReturn(document);
		when(authFacade.getCurrentDetails())
			.thenReturn(authDetails);
		when(authFacade.getCurrentDetails().getRole())
			.thenReturn(Type.STUDENT);
		when(studentFacade.getCurrentStudent())
			.thenReturn(student);

		assertThrows(InvalidRoleException.class, () -> service.execute(uuid.toString()));
	}

	@Test
	void 권한있는_학생() {
		when(documentFacade.getDocument(uuid))
			.thenReturn(document);
		when(authFacade.getCurrentDetails())
			.thenReturn(authDetails);
		when(authFacade.getCurrentDetails().getRole())
			.thenReturn(Type.STUDENT);
		when(studentFacade.getCurrentStudent())
			.thenReturn(student);

		assertDoesNotThrow(() -> service.execute(uuid.toString()));
	}

	@Test
	void 빈_문서() {
		List<PublicDocument> publicDocumentList = new ArrayList<>();

		when(documentFacade.getDocument(uuid))
			.thenReturn(document);
		when(authFacade.getCurrentDetails())
			.thenReturn(authDetails);
		when(authFacade.getCurrentDetails().getRole())
			.thenReturn(Type.ROOT);
		when(publicDocumentRepository.findByIdDocumentId(uuid))
			.thenReturn(publicDocumentList);

		willThrow(DocumentNotFoundException.class).given(documentFacade).assertNotEmpty(publicDocumentList);
	}
}
