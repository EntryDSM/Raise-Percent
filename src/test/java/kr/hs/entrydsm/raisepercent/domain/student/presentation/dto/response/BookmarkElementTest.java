package kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookmarkElementTest {

    private final String companyName = "Entry";

    private final String managerEmail = "test@gmail.com";

    private final BookmarkElement element = new BookmarkElement(companyName, managerEmail);

    @Test
    void 회사명_가져오기() {
        assertThat(companyName).isEqualTo(element.getCompanyName());
    }

    @Test
    void 인사담당자_이메일_가져오기() {
        assertThat(managerEmail).isEqualTo(element.getManagerEmail());
    }

}