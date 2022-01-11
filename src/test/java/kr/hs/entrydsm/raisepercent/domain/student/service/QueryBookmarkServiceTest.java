package kr.hs.entrydsm.raisepercent.domain.student.service;

import kr.hs.entrydsm.raisepercent.domain.bookmark.domain.Bookmark;
import kr.hs.entrydsm.raisepercent.domain.bookmark.domain.repositories.BookmarkRepository;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QueryBookmarkServiceTest {

    private static final StudentFacade studentFacade = mock(StudentFacade.class);

    private static final BookmarkRepository bookmarkRepository = mock(BookmarkRepository.class);

    private static final QueryBookmarkService service = new QueryBookmarkService(studentFacade, bookmarkRepository);

    @Test
    void 북마크_가져오기() {
        Student student = Student.builder().build();
        List<Bookmark> bookmarks = List.of(Bookmark.builder().build());

        when(studentFacade.getCurrentStudent())
                .thenReturn(student);
        when(bookmarkRepository.findByStudent(student))
                .thenReturn(bookmarks);

        service.execute();
    }

}