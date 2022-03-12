package kr.hs.entrydsm.raisepercent.domain.student.service;

import kr.hs.entrydsm.raisepercent.domain.bookmark.domain.Bookmark;
import kr.hs.entrydsm.raisepercent.domain.bookmark.domain.repositories.BookmarkRepository;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import kr.hs.entrydsm.raisepercent.domain.student.facade.StudentFacade;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response.BookmarkElement;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response.BookmarkListResponse;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class QueryBookmarkServiceTest {

    @Mock
    private StudentFacade studentFacade;

    @Mock
    private BookmarkRepository bookmarkRepository;

    @InjectMocks
    private QueryBookmarkService service;

    @Test
    void 북마크_가져오기() {
        //given
        Student student = Student.builder().build();
        Bookmark bookmark = Bookmark.builder().build();
        List<Bookmark> bookmarks = List.of(bookmark);

        BookmarkElement element = new BookmarkElement(bookmark.getCompanyName(), bookmark.queryHrEmail());
        BookmarkListResponse response = new BookmarkListResponse(List.of(element));

        given(studentFacade.getCurrentStudent())
                .willReturn(student);
        given(bookmarkRepository.findByStudent(student))
                .willReturn(bookmarks);
        //when
        BookmarkListResponse execute = service.execute();

        //then
        assertThat(response).usingRecursiveComparison().isEqualTo(execute);
    }

}