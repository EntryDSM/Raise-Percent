package kr.hs.entrydsm.raisepercent.domain.document.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;
import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.PublicDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.facade.DocumentFacade;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import org.junit.jupiter.api.Test;

class DeletePublicDocumentServiceTest {

	private static final PublicDocumentRepository publicDocumentRepository = mock(PublicDocumentRepository.class);

	private static final DocumentFacade documentFacade = mock(DocumentFacade.class);

	private static final StudentFacade studentFacade = mock(StudentFacade.class);

	private static final DeletePublicDocumentService service = new DeletePublicDocumentService(publicDocumentRepository, documentFacade, studentFacade);

	private static final UUID uuid = UUID.randomUUID();

	private static final Document document = UpdateDocumentServiceTest.document;

	private static final Student studnet = UpdateDocumentServiceTest.student;

	@Test
	void 공개문서_삭제() {

		when(documentFacade.getDocument(uuid))
			.thenReturn(document);
		when(studentFacade.getCurrentStudent())
			.thenReturn(studnet);

		service.execute(uuid.toString());

		verify(publicDocumentRepository, times(1)).deleteByIdDocumentId(uuid);
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
}
