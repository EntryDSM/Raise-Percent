package kr.hs.entrydsm.raisepercent.domain.bookmark.service;

import kr.hs.entrydsm.raisepercent.domain.bookmark.domain.Bookmark;
import kr.hs.entrydsm.raisepercent.domain.bookmark.domain.repositories.BookmarkRepository;
import kr.hs.entrydsm.raisepercent.domain.bookmark.exception.AlreadyRegisteredBookmark;
import kr.hs.entrydsm.raisepercent.domain.hr.domain.Hr;
import kr.hs.entrydsm.raisepercent.domain.hr.facade.HrFacade;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterBookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final StudentFacade studentFacade;
    private final HrFacade hrFacade;

    public void execute(String studentEmail) {
        Student student = studentFacade.getStudent(studentEmail);
        Hr hr = hrFacade.getHr();
        String companyName = hr.getCompany().getName();

        if (bookmarkRepository.findByHrAndStudentEmail(hr,studentEmail).isPresent()) {
            throw AlreadyRegisteredBookmark.EXCEPTION;
        }

        bookmarkRepository.save(
                Bookmark.builder()
                        .student(student)
                        .hr(hr)
                        .companyName(companyName)
                        .build()
        );

    }

}
