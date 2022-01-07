package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.facade.UserFacade;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.UpdateInformationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateInformationService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(UpdateInformationRequest request) {
        User user = userFacade.getCurrentUser();

        user.setContactTel(request.getContactTel());
        user.setContactEmail(request.getContactEmail());
    }

}
