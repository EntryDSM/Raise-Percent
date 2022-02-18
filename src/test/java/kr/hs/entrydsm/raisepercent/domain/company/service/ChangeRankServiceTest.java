package kr.hs.entrydsm.raisepercent.domain.company.service;

import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import kr.hs.entrydsm.raisepercent.domain.company.domain.repositories.CompanyRepository;
import kr.hs.entrydsm.raisepercent.domain.company.domain.types.Rank;
import kr.hs.entrydsm.raisepercent.domain.company.facade.CompanyFacade;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class ChangeRankServiceTest {

    private static final CompanyFacade companyFacade = mock(CompanyFacade.class);

    private static final CompanyRepository companyRepository = mock(CompanyRepository.class);

    private static final ChangeRankService changeRankService = new ChangeRankService(
            companyRepository,
            companyFacade
    );

    Company company = Company.builder().build();

    Company senior = Company.builder()
            .name("original senior")
            .rankValue(Rank.SENIOR)
            .build();

    Company junior = Company.builder()
            .name("original junior")
            .rankValue(Rank.JUNIOR)
            .build();

    @Test
    public void 회사_랭크_변경하기(){
        when(companyFacade.getCompany(anyString()))
                .thenReturn(company);

        when(companyRepository.findByName(anyString()))
                .thenReturn(Optional.of(company));

        assertEquals(Rank.SENIOR, senior.getRankValue());

        assertEquals(Rank.JUNIOR, junior.getRankValue());

        senior.updateRank(Rank.JUNIOR);

        junior.updateRank(Rank.SENIOR);

        assertThat(senior.getRankValue()).isEqualTo(Rank.JUNIOR);

        assertThat(junior.getRankValue()).isEqualTo(Rank.SENIOR);
    }

}
