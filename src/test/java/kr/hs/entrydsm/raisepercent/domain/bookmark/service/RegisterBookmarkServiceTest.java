package kr.hs.entrydsm.raisepercent.domain.bookmark.service;

import kr.hs.entrydsm.raisepercent.domain.bookmark.domain.Bookmark;
import kr.hs.entrydsm.raisepercent.domain.bookmark.domain.repositories.BookmarkRepository;
import kr.hs.entrydsm.raisepercent.domain.hr.domain.Hr;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;


public class RegisterBookmarkServiceTest {


    private static final RegisterBookmarkService registerBookmarkService = mock(RegisterBookmarkService.class);

    private static final BookmarkRepository bookmarkRepository = mock(BookmarkRepository.class);

    @Test
    public void 북마크_등록하기() {
        String studentEmail = "example@dsm.hs.kr";

        Hr hr = Hr.builder().build();

        Bookmark bookmark = Bookmark.builder().build();

        when(bookmarkRepository.findByHrAndStudentEmail(hr,studentEmail))
                .thenReturn(Optional.empty());

        registerBookmarkService.execute(studentEmail);

        when(bookmarkRepository.findByHrAndStudentEmail(hr,studentEmail))
                .thenReturn(Optional.of(bookmark));
    }

}
