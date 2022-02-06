package kr.hs.entrydsm.raisepercent.domain.document.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.LocalDocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.domain.types.DocumentType;
import kr.hs.entrydsm.raisepercent.domain.document.facade.DocumentFacade;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.request.UpdateDocumentRequest;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.request.UpdateDocumentRequest.PageRequest;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.global.exception.DocumentNotFoundException;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class UpdateDocumentServiceTest {

	private static final LocalDocumentRepository localDocumentRepository = mock(LocalDocumentRepository.class);

	private static final DocumentFacade documentFacade = mock(DocumentFacade.class);

	private static final StudentFacade studentFacade = mock(StudentFacade.class);

	private static final UpdateDocumentService service = new UpdateDocumentService(localDocumentRepository, documentFacade, studentFacade);

	private static final String email = "test@nate.com";

	private static final String name = "test";

	private static final UUID uuid = UUID.randomUUID();

	private static final User user = User.builder()
		.email(email)
		.name(name)
		.build();

	private static final Student student = Student.builder()
		.number("1415")
		.year("2022")
		.user(user)
		.build();

	private static final Document document = Document.builder()
		.documentType(DocumentType.RESUME)
		.student(student)
		.build();

	private static final UpdateDocumentRequest request = new UpdateDocumentRequest();

	private static final PageRequest pageRequest = new PageRequest();

	@Test
	void 문서_수정() {

		final String content = "test";
		final Integer page = 1;

		setField(pageRequest, "content", content);
		setField(pageRequest, "page", page);
		setField(request, "pages", List.of(pageRequest));

		when(documentFacade.getDocument(uuid))
			.thenReturn(document);
		when(studentFacade.getCurrentStudent())
			.thenReturn(student);

		service.execute(uuid, request);

		verify(localDocumentRepository, times(1)).deleteByIdDocumentId(uuid);
		verify(localDocumentRepository, times(1)).saveAll(any());

	}

	@Test
	void 문서_수정_권한_없음() {
		Student student = Student.builder().build();

		when(documentFacade.getDocument(uuid))
			.thenReturn(document);
		when(studentFacade.getCurrentStudent())
			.thenReturn(student);

		assertThrows(InvalidRoleException.class, () -> service.execute(uuid, request));
	}
}
