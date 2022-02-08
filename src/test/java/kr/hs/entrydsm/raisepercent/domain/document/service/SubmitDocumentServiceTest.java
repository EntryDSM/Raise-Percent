package kr.hs.entrydsm.raisepercent.domain.document.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;
import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.LocalDocument;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.LocalDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.StayDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.SubmittedDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.facade.DocumentFacade;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.global.entity.DocumentContentId;
import kr.hs.entrydsm.raisepercent.global.exception.DocumentNotFoundException;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import org.junit.jupiter.api.Test;

class SubmitDocumentServiceTest {

	private static final LocalDocumentRepository localDocumentRepository = mock(LocalDocumentRepository.class);

	private static final StayDocumentRepository stayDocumentRepository = mock(StayDocumentRepository.class);

	private static final SubmittedDocumentRepository submittedDocumentRepository = mock(SubmittedDocumentRepository.class);

	private static final DocumentFacade documentFacade = mock(DocumentFacade.class);

	private static final StudentFacade studentFacade = mock(StudentFacade.class);

	private static final SubmitDocumentService service = new SubmitDocumentService(localDocumentRepository, stayDocumentRepository, submittedDocumentRepository, documentFacade, studentFacade);

	private static final UUID uuid = UUID.randomUUID();

	private static final Document document = UpdateDocumentServiceTest.document;

	private static final Student student = UpdateDocumentServiceTest.student;

	private static final LocalDocument localDocument = LocalDocument.builder()
		.id(DocumentContentId.builder()
			.page(1)
			.document(document)
			.build())
		.content("test")
		.build();

	@Test
	void 문서_제출() {

		when(documentFacade.getDocument(uuid))
			.thenReturn(document);
		when(studentFacade.getCurrentStudent())
			.thenReturn(student);
		when(localDocumentRepository.findByIdDocumentId(uuid))
			.thenReturn(List.of(localDocument));

		service.execute(uuid.toString());

		verify(localDocumentRepository, times(1)).findByIdDocumentId(uuid);
		verify(stayDocumentRepository, times(1)).saveAll(any());
		verify(submittedDocumentRepository, times(1)).save(any());
	}

	@Test
	void 권한없음() {
		Student student = Student.builder().build();

		when(documentFacade.getDocument(uuid))
			.thenReturn(document);
		when(studentFacade.getCurrentStudent())
			.thenReturn(student);

		assertThrows(InvalidRoleException.class, () -> service.execute(uuid.toString()));
	}

	@Test
	void 문서_비어있음() {

		when(documentFacade.getDocument(uuid))
			.thenReturn(document);
		when(studentFacade.getCurrentStudent())
			.thenReturn(student);
		when(localDocumentRepository.findByIdDocumentId(uuid))
			.thenReturn(List.of());

		assertThrows(DocumentNotFoundException.class, () -> service.execute(uuid.toString()));
	}

}
