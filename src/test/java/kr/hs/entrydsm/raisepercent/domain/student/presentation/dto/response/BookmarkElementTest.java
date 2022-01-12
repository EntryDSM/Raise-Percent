package kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkElementTest {

    private static final String companyName = "Entry";

    private static final String managerEmail = "test@gmail.com";

    private static final BookmarkElement element = new BookmarkElement(companyName, managerEmail);

    @Test
    void 회사명_가져오기() {
        assertEquals(companyName, element.getCompanyName());
    }

    @Test
    void 인사담당자_이메일_가져오기() {
        assertEquals(managerEmail, element.getManagerEmail());
    }

}