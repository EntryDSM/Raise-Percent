package kr.hs.entrydsm.raisepercent.domain.document.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.DocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.request.CreateDocumentRequest;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateDocumentServiceTest {

	@Mock
	private static DocumentRepository documentRepository;

	@Mock private static StudentRepository studentRepository;

	@Mock private static AuthFacade authFacade;

	@Mock private static CreateDocumentRequest request;

	@InjectMocks
	private static CreateDocumentService service;

	@Test
	void 문서_생성() {
		final String username = "test@dsm.hs.kr";
		final kr.hs.entrydsm.raisepercent.domain.document.domain.types.Type type = kr.hs.entrydsm.raisepercent.domain.document.domain.types.Type.PORTFOLIO;

		Student student = Student.builder().build();
		when(authFacade.getCurrentDetails())
			.thenReturn(new AuthDetails(username, Type.TEACHER));
		when(studentRepository.findById(any()))
			.thenReturn(Optional.ofNullable(student));
		when(request.getType())
			.thenReturn(type);
		when(documentRepository.save(any()))
			.thenReturn(Document.builder().build());

		service.execute(request);

		verify(documentRepository, times(1)).save(any());
	}

}
