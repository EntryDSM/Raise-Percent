package kr.hs.entrydsm.raisepercent.domain.bookmark.domain;

import kr.hs.entrydsm.raisepercent.domain.hr.domain.Hr;
import kr.hs.entrydsm.raisepercent.domain.student.domain.Student;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class BookmarkTest {

    private final Hr hr = Hr.builder()
            .build();

    private final Student student = Student.builder()
            .build();

    private final String companyName = "Test name";

    private final Bookmark bookmark = Bookmark.builder()
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
        assertThat(hr).isEqualTo(bookmark.getHr());
    }

    @Test
    void 북마크_학생_가져오기() {
        assertThat(student).isEqualTo(bookmark.getStudent());
    }

    @Test
    void 북마크_회사명_가져오기() {
        assertThat(companyName).isEqualTo(bookmark.getCompanyName());
    }

}