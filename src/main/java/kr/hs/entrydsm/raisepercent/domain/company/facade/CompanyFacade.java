package kr.hs.entrydsm.raisepercent.domain.company.facade;

import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import kr.hs.entrydsm.raisepercent.domain.company.domain.repositories.CompanyRepository;
import kr.hs.entrydsm.raisepercent.global.exception.CompanyNotFoundException;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CompanyFacade {

    private final CompanyRepository companyRepository;

    public Company getCompany(String companyName){
        Company company = companyRepository.findById(UUIDUtil.convertToUUID(companyName))
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        return company;
    }

}