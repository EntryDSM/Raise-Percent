package kr.hs.entrydsm.raisepercent.domain.document.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.util.ReflectionTestUtils.setField;

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
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateDocumentServiceTest {

	@Mock
	private LocalDocumentRepository localDocumentRepository;

	@Mock
	private DocumentFacade documentFacade;

	@Mock
	private StudentFacade studentFacade;

	@InjectMocks
	private UpdateDocumentService service;

	private static final String email = "test@nate.com";

	private static final String name = "test";

	private static final UUID uuid = UUID.randomUUID();

	private static final User user = User.builder()
		.email(email)
		.name(name)
		.build();

	public static final Student student = Student.builder()
		.number("1415")
		.year("2022")
		.user(user)
		.build();

	public static final Document document = Document.builder()
		.documentType(DocumentType.RESUME)
		.student(student)
		.build();

	private static final UpdateDocumentRequest request = new UpdateDocumentRequest();

	private static final PageRequest pageRequest = new PageRequest();

	@Test
	void 문서_수정() {
		//given
		String content = "test";
		Integer page = 1;

		setField(pageRequest, "content", content);
		setField(pageRequest, "page", page);
		setField(request, "pages", List.of(pageRequest));

		given(documentFacade.getDocument(uuid))
			.willReturn(document);
		given(studentFacade.getCurrentStudent())
			.willReturn(student);

		//when
		service.execute(uuid, request);

		//then
		then(localDocumentRepository).should(times(1)).deleteByIdDocumentId(uuid);
		then(localDocumentRepository).should(times(1)).saveAll(any());

	}

	@Test
	void 문서_수정_권한_없음() {
		//given
		Student student = Student.builder().build();

		given(documentFacade.getDocument(uuid))
			.willReturn(document);
		given(studentFacade.getCurrentStudent())
			.willReturn(student);

		//when then
		assertThrows(InvalidRoleException.class, () -> service.execute(uuid, request));
	}
}
