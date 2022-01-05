package kr.hs.entrydsm.raisepercent.domain.user.facade;

import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.global.exception.CredentialsNotFoundException;
import kr.hs.entrydsm.raisepercent.global.exception.UserNotFoundException;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        return userRepository.findById(getCurrentDetails().getUsername())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    private AuthDetails getCurrentDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof AuthDetails)) {
            throw CredentialsNotFoundException.EXCEPTION;
        }
        return (AuthDetails) principal;
    }

}
