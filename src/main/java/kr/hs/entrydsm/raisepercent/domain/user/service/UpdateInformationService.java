package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.UpdateInformationRequest;
import kr.hs.entrydsm.raisepercent.global.exception.UserNotFoundException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateInformationService {

    private final UserRepository userRepository;
    private final AuthFacade authFacade;

    @Transactional
    public void execute(UpdateInformationRequest request) {
        User user = userRepository.findById(authFacade.getCurrentDetails().getUsername())
            .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        user.setContactTel(request.getContactTel());
        user.setContactEmail(request.getContactEmail());
    }

}
