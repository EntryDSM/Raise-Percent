package kr.hs.entrydsm.raisepercent.domain.feedback.service;

import kr.hs.entrydsm.raisepercent.domain.document.domain.Document;
import kr.hs.entrydsm.raisepercent.domain.document.facade.DocumentFacade;
import kr.hs.entrydsm.raisepercent.domain.feedback.domain.Feedback;
import kr.hs.entrydsm.raisepercent.domain.feedback.domain.repositories.FeedbackRepository;
import kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.request.CreateFeedbackRequest;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.facade.TeacherFacade;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateFeedbackService {

    private final TeacherFacade teacherFacade;
    private final DocumentFacade documentFacade;
    private final FeedbackRepository feedbackRepository;

    public void execute(String id, CreateFeedbackRequest request) {

        Teacher teacher = teacherFacade.getCurrentTeacher();

        Document document = documentFacade.getDocument(UUIDUtil.convertToUUID(id));

        feedbackRepository.save(Feedback.builder()
                .content(request.getContent())
                .teacher(teacher)
                .document(document)
                .isWatch(false)
                .build());
    }
}
