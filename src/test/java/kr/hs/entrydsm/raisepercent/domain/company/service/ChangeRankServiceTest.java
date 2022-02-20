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

    Company junior = Company.builder()
            .name("naver")
            .rankValue(Rank.JUNIOR)
            .build();

    Company senior = Company.builder()
            .name("google")
            .rankValue(Rank.SENIOR)
            .build();

    @Test
    public void 회사_랭크_변경하기(){
        String juniorCompanyName = "naver";
        String seniorCompanyName = "google";

        when(companyRepository.findByName(anyString()))
                .thenReturn(Optional.of(company));

        when(companyRepository.findByName(juniorCompanyName))
                .thenReturn(Optional.of(junior));

        when(companyRepository.findByName(seniorCompanyName))
                .thenReturn(Optional.of(senior));

        when(companyFacade.getCompany(anyString()))
                .thenReturn(company);

        when(companyFacade.getCompany(juniorCompanyName))
                .thenReturn(junior);

        when(companyFacade.getCompany(seniorCompanyName))
                .thenReturn(senior);

        changeRankService.execute(juniorCompanyName);

        changeRankService.execute(seniorCompanyName);

        assertEquals(Rank.JUNIOR,senior.getRankValue());

        assertEquals(Rank.SENIOR,junior.getRankValue());
    }

}
