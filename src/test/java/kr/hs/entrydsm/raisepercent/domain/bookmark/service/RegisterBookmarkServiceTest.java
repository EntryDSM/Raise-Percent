package kr.hs.entrydsm.raisepercent.domain.bookmark.service;

import kr.hs.entrydsm.raisepercent.domain.bookmark.domain.Bookmark;
import kr.hs.entrydsm.raisepercent.domain.bookmark.domain.repositories.BookmarkRepository;
import kr.hs.entrydsm.raisepercent.domain.hr.domain.Hr;
import kr.hs.entrydsm.raisepercent.domain.hr.facade.HrFacade;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.domain.repositories.StudentRepository;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import org.mockito.Mock;
import org.mockito.InjectMocks;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

public class RegisterBookmarkServiceTest {

    private static final RegisterBookmarkService registerBookmarkService = mock(RegisterBookmarkService.class);

    private static final BookmarkRepository bookmarkRepository = mock(BookmarkRepository.class);

    private static final StudentFacade studentFacade = mock(StudentFacade.class);


    @Test
    public void 북마크_등록하기() {
        String studentEmail = "example@dsm.hs.kr";

        Hr hr = Hr.builder().build();
        Bookmark bookmark = Bookmark.builder().build();
        Student student = studentFacade.getStudent(studentEmail);

        when(bookmarkRepository.findByHrAndStudent(hr,student))
                .thenReturn(Optional.empty());

        registerBookmarkService.execute(studentEmail);

        when(bookmarkRepository.findByHrAndStudent(hr,student))
                .thenReturn(Optional.of(bookmark));
    }

}
