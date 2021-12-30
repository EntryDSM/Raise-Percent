package kr.hs.entrydsm.raisepercent.domain.bookmark.domain;

import kr.hs.entrydsm.raisepercent.domain.hr.domain.Hr;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkTest {

    private static final Hr hr = Hr.builder()
            .build();

    private static final Student student = Student.builder()
            .build();

    private static final String companyName = "Test name";

    private static final Bookmark bookmark = Bookmark.builder()
            .hr(hr)
            .student(student)
            .companyName(companyName)
            .build();

    @Test
    void 북마크_객체_생성() {
        Bookmark bookmark = new Bookmark();
        assertNull(bookmark.getId());
        assertNull(bookmark.getHr());
        assertNull(bookmark.getStudent());
        assertNull(bookmark.getCompanyName());
        assertNull(bookmark.getCreatedAt());
    }

    @Test
    void 북마크_인사담당자_가져오기() {
        assertEquals(hr, bookmark.getHr());
    }

    @Test
    void 북마크_학생_가져오기() {
        assertEquals(student, bookmark.getStudent());
    }

    @Test
    void 북마크_회사명_가져오기() {
        assertEquals(companyName, bookmark.getCompanyName());
    }

}