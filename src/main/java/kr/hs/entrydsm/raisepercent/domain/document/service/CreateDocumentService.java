package kr.hs.entrydsm.raisepercent.domain.document.service;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.domain.repositories.DocumentRepository;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.request.CreateDocumentRequest;
import kr.hs.entrydsm.raisepercent.domain.document.presentation.dto.response.CreateDocumentResponse;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDocumentService {

	private final DocumentRepository documentRepository;
	private final StudentFacade studentFacade;

	public CreateDocumentResponse execute(CreateDocumentRequest request) {
		Student student = studentFacade.getCurrentStudent();

		Document document = documentRepository.save(
			Document.builder()
				.student(student)
				.documentType(request.getDocumentType())
				.build()
		);

		return new CreateDocumentResponse(document.getId());
	}
}
