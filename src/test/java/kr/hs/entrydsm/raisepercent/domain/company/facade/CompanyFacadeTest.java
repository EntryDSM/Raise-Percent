package kr.hs.entrydsm.raisepercent.domain.company.facade;

import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import kr.hs.entrydsm.raisepercent.domain.company.domain.repositories.CompanyRepository;
import kr.hs.entrydsm.raisepercent.domain.company.exception.CompanyNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CompanyFacadeTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyFacade companyFacade;

    @Test
    public void 회사_가져오기(){
        //given
        String name = "google";

        Company company = Company.builder()
                .name(name)
                .build();

        given(companyRepository.findByName(name))
                .willReturn(Optional.of(company));

        //when then
        assertEquals(company,companyFacade.getCompany(name));
    }

    @Test
    public void 회사_예외(){
        //given
        String name = "google";

        given(companyRepository.findByName(name))
                .willReturn(Optional.empty());

        //when then
        assertThrows(CompanyNotFoundException.class, () -> companyFacade.getCompany(name));
    }

}