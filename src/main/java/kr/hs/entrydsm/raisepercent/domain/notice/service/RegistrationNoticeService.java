package kr.hs.entrydsm.raisepercent.domain.notice.service;

import kr.hs.entrydsm.raisepercent.domain.notice.domain.Notice;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories.NoticeRepository;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.types.Scope;
import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.request.RegistrationNoticeRequest;
import kr.hs.entrydsm.raisepercent.domain.teacher.domain.Teacher;
import kr.hs.entrydsm.raisepercent.domain.teacher.facade.TeacherFacade;
import kr.hs.entrydsm.raisepercent.global.util.EnumUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationNoticeService {

    private final TeacherFacade teacherFacade;
    private final NoticeRepository noticeRepository;
    
    public void execute(RegistrationNoticeRequest request) {
        Teacher teacher = teacherFacade.getCurrentTeacher();
        Scope scope = EnumUtil.convertToEnum(request.getScope(), Scope.class);

        Notice notice = Notice.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .scope(scope)
                .teacher(teacher)
                .build();

        noticeRepository.save(notice);
    }

}
