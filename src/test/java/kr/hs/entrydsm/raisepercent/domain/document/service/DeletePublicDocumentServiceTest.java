package kr.hs.entrydsm.raisepercent.domain.document.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import java.util.UUID;
import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.PublicDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.facade.DocumentFacade;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeletePublicDocumentServiceTest {

	@Mock
	private PublicDocumentRepository publicDocumentRepository;

	@Mock
	private DocumentFacade documentFacade;

	@Mock
	private StudentFacade studentFacade;

	@InjectMocks
	private DeletePublicDocumentService service;

	private final UUID uuid = UUID.randomUUID();

	private final Document document = UpdateDocumentServiceTest.document;

	private final Student studnet = UpdateDocumentServiceTest.student;

	@Test
	void 공개문서_삭제() {
		//given
		given(documentFacade.getDocument(uuid))
			.willReturn(document);
		given(studentFacade.getCurrentStudent())
			.willReturn(studnet);

		//when
		service.execute(uuid.toString());

		//then
		then(publicDocumentRepository).should(times(1)).deleteByIdDocument(document);
	}

	@Test
	void 권한없음() {
		//given
		Student student = Student.builder().build();

		given(documentFacade.getDocument(uuid))
			.willReturn(document);
		given(studentFacade.getCurrentStudent())
			.willReturn(student);

		//when then
		assertThrows(InvalidRoleException.class, () -> service.execute(uuid.toString()));
	}
}
