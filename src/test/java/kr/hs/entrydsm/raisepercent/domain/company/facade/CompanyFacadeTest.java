package kr.hs.entrydsm.raisepercent.domain.company.facade;

import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import kr.hs.entrydsm.raisepercent.domain.company.domain.repositories.CompanyRepository;
import kr.hs.entrydsm.raisepercent.domain.company.exception.CompanyNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CompanyFacadeTest {

    private static final CompanyRepository companyRepository = mock(CompanyRepository.class);
    private static final CompanyFacade companyFacade = new CompanyFacade(companyRepository);

    @Test
    public void 회사_가져오기(){
        String name = "google";

        Company company = Company.builder()
                .name(name)
                .build();

        when(companyRepository.findByName(name))
                .thenReturn(Optional.of(company));

        assertEquals(company,companyFacade.getCompany(name));
    }

    @Test
    public void 회사_예외(){
        String name = "google";

        when(companyRepository.findByName(name))
                .thenReturn(Optional.empty());

        assertThrows(CompanyNotFoundException.class, () -> companyFacade.getCompany(name));
    }

}