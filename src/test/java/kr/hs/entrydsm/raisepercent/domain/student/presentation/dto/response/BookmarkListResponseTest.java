package kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookmarkListResponseTest {

    private static final List<BookmarkElement> elements = new ArrayList<>();

    private static final BookmarkListResponse response = new BookmarkListResponse(elements);

    @Test
    void 리스트_가져오기() {
        assertEquals(elements, response.getCompanyList());
    }

}