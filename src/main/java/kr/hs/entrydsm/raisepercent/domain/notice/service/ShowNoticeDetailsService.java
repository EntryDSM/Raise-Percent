package kr.hs.entrydsm.raisepercent.domain.notice.service;

import kr.hs.entrydsm.raisepercent.domain.hr.domain.repositories.HrRepository;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.Notice;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.repositories.NoticeRepository;
import kr.hs.entrydsm.raisepercent.domain.notice.domain.types.Scope;
import kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.response.NoticeDetailsResponse;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.facade.UserFacade;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidRoleException;
import kr.hs.entrydsm.raisepercent.global.exception.NoticeNotFoundException;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ShowNoticeDetailsService {

    private final NoticeRepository noticeRepository;
    private final UserFacade userFacade;
    private final HrRepository hrRepository;
    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public NoticeDetailsResponse execute(String noticeId) {
        Notice notice = noticeRepository.findById(UUIDUtil.convertToUUID(noticeId))
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        User user = userFacade.getCurrentUser();

        if (Scope.STUDENT.equals(notice.getScope()) && studentRepository.findById(user.getEmail()).isEmpty()) {
            throw InvalidRoleException.EXCEPTION;
        } else if (Scope.COMPANY.equals(notice.getScope()) && hrRepository.findById(user.getEmail()).isEmpty()) {
            throw InvalidRoleException.EXCEPTION;
        }

        return NoticeDetailsResponse.builder()
                .title(notice.getTitle())
                .content(notice.getContent())
                .scope(notice.getScope())
                .createdAt(notice.getCreatedAt())
                .teacherEmail(notice.getTeacher().getEmail())
                .build();
    }
}
