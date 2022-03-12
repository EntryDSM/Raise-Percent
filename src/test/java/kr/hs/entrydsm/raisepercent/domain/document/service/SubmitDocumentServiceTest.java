package kr.hs.entrydsm.raisepercent.domain.document.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;

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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SubmitDocumentServiceTest {

	@Mock
	private LocalDocumentRepository localDocumentRepository;

	@Mock
	private StayDocumentRepository stayDocumentRepository;

	@Mock
	private SubmittedDocumentRepository submittedDocumentRepository;

	@Mock
	private DocumentFacade documentFacade;

	@Mock
	private StudentFacade studentFacade;

	@InjectMocks
	private SubmitDocumentService service;

	private final UUID uuid = UUID.randomUUID();

	private final Document document = UpdateDocumentServiceTest.document;

	private final Student student = UpdateDocumentServiceTest.student;

	private final LocalDocument localDocument = LocalDocument.builder()
		.id(DocumentContentId.builder()
			.page(1)
			.document(document)
			.build())
		.content("test")
		.build();

	@Test
	void 문서_제출() {
		//given
		given(documentFacade.getDocument(uuid))
			.willReturn(document);
		given(studentFacade.getCurrentStudent())
			.willReturn(student);
		given(localDocumentRepository.findByIdDocumentId(uuid))
			.willReturn(List.of(localDocument));

		//when
		service.execute(uuid.toString());

		//then
		then(localDocumentRepository).should(atLeast(1)).findByIdDocumentId(uuid);
		then(stayDocumentRepository).should(times(1)).saveAll(any());
		then(submittedDocumentRepository).should(times(1)).save(any());
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

	@Test
	void 문서_비어있음() {
		//given
		given(documentFacade.getDocument(uuid))
			.willReturn(document);
		given(studentFacade.getCurrentStudent())
			.willReturn(student);
		given(localDocumentRepository.findByIdDocumentId(uuid))
			.willReturn(List.of());

		//when then
		assertThrows(DocumentNotFoundException.class, () -> service.execute(uuid.toString()));
	}

}
