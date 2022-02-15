package kr.hs.entrydsm.raisepercent.domain.company.facade;

import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import kr.hs.entrydsm.raisepercent.domain.company.domain.repositories.CompanyRepository;
import kr.hs.entrydsm.raisepercent.global.exception.CompanyNotFoundException;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class CompanyFacade {

    private final CompanyRepository companyRepository;

    public Company getCompany(UUID companyId){
        return companyRepository.findById(companyId)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
    }

}