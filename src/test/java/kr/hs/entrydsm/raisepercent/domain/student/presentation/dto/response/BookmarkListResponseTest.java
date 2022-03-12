package kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookmarkListResponseTest {

    private final List<BookmarkElement> elements = new ArrayList<>();

    private final BookmarkListResponse response = new BookmarkListResponse(elements);

    @Test
    void 리스트_가져오기() {
        assertThat(elements).isEqualTo(response.getCompanyList());
    }

}