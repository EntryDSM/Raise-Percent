package kr.hs.entrydsm.raisepercent.domain.company.service;

import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import kr.hs.entrydsm.raisepercent.domain.company.domain.repositories.CompanyRepository;
import kr.hs.entrydsm.raisepercent.domain.company.domain.types.Rank;
import kr.hs.entrydsm.raisepercent.domain.company.facade.CompanyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChangeRankService {

    private final CompanyRepository companyRepository;
    private final CompanyFacade companyFacade;

    public void execute(String name){
        Company company = companyFacade.getCompany(name);

        Company changedCompany = changeRank(company);

        companyRepository.delete(company);

        companyRepository.save(changedCompany);
    }

    private Company changeRank(Company company){
        if(company.getRankValue()==Rank.JUNIOR) {
            company.updateRank(Rank.SENIOR);
            return company;
        }
        company.updateRank(Rank.JUNIOR);
        return company;
    }

}
