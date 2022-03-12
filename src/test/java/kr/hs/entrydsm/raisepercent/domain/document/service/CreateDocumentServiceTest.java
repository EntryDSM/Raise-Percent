package kr.hs.entrydsm.raisepercent.domain.document.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.DocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.domain.types.DocumentType;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.request.CreateDocumentRequest;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateDocumentServiceTest {

	@Mock
	private DocumentRepository documentRepository;

	@Mock
	private StudentFacade studentFacade;

	@Mock
	private CreateDocumentRequest request;

	@InjectMocks
	private CreateDocumentService service;

	@Test
	void 문서_생성() {
		//given
		DocumentType documentType = DocumentType.PORTFOLIO;

		Student student = Student.builder().build();

		given(studentFacade.getCurrentStudent())
			.willReturn(student);
		given(request.getDocumentType())
			.willReturn(documentType);
		given(documentRepository.save(any()))
			.willReturn(Document.builder().build());

		//when
		service.execute(request);

		//then
		then(documentRepository).should(times(1)).save(any());
	}

}
