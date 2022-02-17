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
        Rank seniorRank = Rank.SENIOR;
        Rank juniorRank = Rank.JUNIOR;

        when(companyFacade.getCompany(anyString()))
                .thenReturn(company);

        when(companyRepository.findCompanyByName(anyString()))
                .thenReturn(Optional.of(company));

        assertEquals(Rank.SENIOR, senior.getRankValue());

        assertEquals(Rank.JUNIOR, junior.getRankValue());

        Company juniorToSenior = changeRankService.execute(junior.getName());

        Company seniorToJunior = changeRankService.execute(senior.getName());

        assertEquals(seniorRank, juniorToSenior.getRankValue());

        assertEquals(juniorRank, seniorToJunior.getRankValue());

    }

}
