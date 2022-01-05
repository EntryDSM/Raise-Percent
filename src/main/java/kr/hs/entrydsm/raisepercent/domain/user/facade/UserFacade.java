package kr.hs.entrydsm.raisepercent.domain.user.facade;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.global.exception.UserNotFoundException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;
    private final AuthFacade authFacade;

    public User getCurrentUser() {
        return userRepository.findById(authFacade.getCurrentDetails().getUsername())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

}
