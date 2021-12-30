package kr.hs.entrydsm.raisepercent.domain.hr.domain;

import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HrTest {

    private static final User user = User.builder()
            .build();

    private static final Company company = Company.builder()
            .build();

    private static final Hr hr = Hr.builder()
            .user(user)
            .company(company)
            .build();

    @Test
    void 인사담당자_객체_생성() {
        Hr hr = new Hr();
        assertNull(hr.getEmail());
        assertNull(hr.getUser());
        assertNull(hr.getCompany());
    }

    @Test
    void 인사담당자_유저_가져오기() {
        assertEquals(user, hr.getUser());
    }

    @Test
    void 인사담당자_회사_가져오기() {
        assertEquals(company, hr.getCompany());
    }

}