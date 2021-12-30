package kr.hs.entrydsm.raisepercent.domain.company.domain;

import kr.hs.entrydsm.raisepercent.domain.company.domain.types.Rank;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompanyTest {

    private static final String name = "Test company";

    private static final Rank rank = Rank.JUNIOR;

    private static final Company company = Company.builder()
            .name(name)
            .rank(rank)
            .build();

    @Test
    void 회사_객체_생성() {
        Company company = new Company();
        assertNull(company.getId());
        assertNull(company.getName());
        assertNull(company.getRank());
    }

    @Test
    void 회사_이름_가져오기() {
        assertEquals(name, company.getName());
    }

    @Test
    @Order(1)
    void 회사_등급_가져오기() {
        assertEquals(rank, company.getRank());
    }

    @Test
    @Order(2)
    void 회사_등급_수정() {
        Rank rank = Rank.SENIOR;
        company.updateRank(rank);
        assertEquals(rank, company.getRank());
    }

}