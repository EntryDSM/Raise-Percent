package kr.hs.entrydsm.raisepercent.domain.document.service;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.DocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.request.CreateDocumentRequest;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response.CreateDocumentResponse;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.global.exception.StudentNotFoundException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDocumentService {

	private final DocumentRepository documentRepository;
	private final StudentRepository studentRepository;
	private final AuthFacade authFacade;

	public CreateDocumentResponse execute(CreateDocumentRequest request) {
		Student student = studentRepository.findById(authFacade.getCurrentDetails().getUsername())
			.orElseThrow(() -> StudentNotFoundException.EXCEPTION);

		Document document = documentRepository.save(
			Document.builder()
				.student(student)
				.type(request.getType())
				.build()
		);

		return new CreateDocumentResponse(document.getId());
	}
}
