package kr.hs.entrydsm.raisepercent.domain.company.service;

import kr.hs.entrydsm.raisepercent.domain.company.domain.Company;
import kr.hs.entrydsm.raisepercent.domain.company.domain.repositories.CompanyRepository;
import kr.hs.entrydsm.raisepercent.domain.company.domain.types.Rank;
import kr.hs.entrydsm.raisepercent.domain.company.facade.CompanyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ChangeRankService {

    private final CompanyRepository companyRepository;
    private final CompanyFacade companyFacade;

    @Transactional
    public void execute(String name){
        Company company = companyFacade.getCompany(name);

        if (company.getRankValue()==Rank.JUNIOR) company.updateRank(Rank.SENIOR);
        else company.updateRank(Rank.SENIOR);

    }

}
