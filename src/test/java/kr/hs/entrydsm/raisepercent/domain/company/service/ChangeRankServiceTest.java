package kr.hs.entrydsm.raisepercent.domain.company.service;

import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import kr.hs.entrydsm.raisepercent.domain.company.domain.repositories.CompanyRepository;
import kr.hs.entrydsm.raisepercent.domain.company.domain.types.Rank;
import kr.hs.entrydsm.raisepercent.domain.company.facade.CompanyFacade;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ChangeRankServiceTest {

    @Mock
    private CompanyFacade companyFacade;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private ChangeRankService changeRankService;

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
        //given
        String juniorCompanyName = "naver";
        String seniorCompanyName = "google";

        given(companyRepository.findByName(anyString()))
                .willReturn(Optional.of(company));

        given(companyRepository.findByName(juniorCompanyName))
                .willReturn(Optional.of(junior));

        given(companyRepository.findByName(seniorCompanyName))
                .willReturn(Optional.of(senior));

        given(companyFacade.getCompany(anyString()))
                .willReturn(company);

        given(companyFacade.getCompany(juniorCompanyName))
                .willReturn(junior);

        given(companyFacade.getCompany(seniorCompanyName))
                .willReturn(senior);

        //when
        changeRankService.execute(juniorCompanyName);
        changeRankService.execute(seniorCompanyName);

        //then
        assertThat(Rank.JUNIOR).isEqualTo(senior.getRankValue());
        assertThat(Rank.SENIOR).isEqualTo(junior.getRankValue());
    }

}
