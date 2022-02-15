package kr.hs.entrydsm.raisepercent.domain.company.facade;

import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import kr.hs.entrydsm.raisepercent.domain.company.domain.repositories.CompanyRepository;
import kr.hs.entrydsm.raisepercent.global.exception.CompanyNotFoundException;
import kr.hs.entrydsm.raisepercent.global.exception.FeedbackNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CompanyFacadeTest {

    private static final CompanyRepository companyRepository = mock(CompanyRepository.class);
    private static final CompanyFacade companyFacade = new CompanyFacade(companyRepository);

    @Test
    public void 회사_가져오기(){
        UUID id = UUID.randomUUID();

        Company company = Company.builder().build();

        when(companyRepository.findById(id))
                .thenReturn(Optional.of(company));

        assertEquals(company,companyFacade.getCompany(id));
    }

    @Test
    public void 회사_예외(){
        UUID id = UUID.randomUUID();

        when(companyRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(CompanyNotFoundException.class, () -> companyFacade.getCompany(id));
    }

}