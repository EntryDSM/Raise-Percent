package kr.hs.entrydsm.raisepercent.domain.hr.domain;

import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import kr.hs.entrydsm.raisepercent.domain.company.domain.types.Rank;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HrTest {

    private static final User user = User.builder()
            .build();

    private static final Company company = Company.builder()
            .rankValue(Rank.JUNIOR)
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

    @Test
    @Order(1)
    void 인사담당자_주니어등급_변환() {
        assertEquals(Type.JUNIOR, hr.queryType());
    }

    @Test
    @Order(2)
    void 인사담당자_시니어등급_변환() {
        User user = User.builder()
                .build();
        Company company = Company.builder()
                .rankValue(Rank.SENIOR)
                .build();
        Hr hr = Hr.builder()
                .user(user)
                .company(company)
                .build();
        assertEquals(Type.SENIOR, hr.queryType());
    }

}