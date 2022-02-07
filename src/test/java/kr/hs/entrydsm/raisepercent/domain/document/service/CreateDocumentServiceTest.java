package kr.hs.entrydsm.raisepercent.domain.document.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.DocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.domain.types.DocumentType;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.request.CreateDocumentRequest;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import org.junit.jupiter.api.Test;

class CreateDocumentServiceTest {

	private static final DocumentRepository documentRepository = mock(DocumentRepository.class);

	private static final StudentFacade studentFacade = mock(StudentFacade.class);

	private static final CreateDocumentRequest request = mock(CreateDocumentRequest.class);

	private static final CreateDocumentService service = new CreateDocumentService(documentRepository, studentFacade);

	@Test
	void 문서_생성() {
		final DocumentType documentType = DocumentType.PORTFOLIO;

		Student student = Student.builder().build();

		when(studentFacade.getCurrentStudent())
			.thenReturn(student);
		when(request.getDocumentType())
			.thenReturn(documentType);
		when(documentRepository.save(any()))
			.thenReturn(Document.builder().build());

		service.execute(request);

		verify(documentRepository, times(1)).save(any());
	}

}
