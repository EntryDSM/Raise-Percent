package kr.hs.entrydsm.raisepercent.domain.hr.facade;

import kr.hs.entrydsm.raisepercent.domain.hr.domain.Hr;
import kr.hs.entrydsm.raisepercent.domain.hr.domain.repositories.HrRepository;
import kr.hs.entrydsm.raisepercent.global.exception.HrNotFoundException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HrFacade {

    private final HrRepository hrRepository;
    private final AuthFacade authFacade;

    public Hr getHr(){
        return hrRepository.findById(authFacade.getCurrentDetails().getUsername())
                .orElseThrow(() -> HrNotFoundException.EXCEPTION);
    }


}
