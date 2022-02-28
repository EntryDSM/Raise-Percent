package kr.hs.entrydsm.raisepercent.domain.company.presentation;

import kr.hs.entrydsm.raisepercent.domain.company.service.ChangeRankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/companies")
@RestController
public class CompanyController {

    private final ChangeRankService changeRankService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{company-name}")
    public void changeRank(@PathVariable("company-name") String companyName){
        changeRankService.execute(companyName);
    }

}
