package kr.hs.entrydsm.raisepercent.global.security.auth;

import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailsService {

    private final AuthFacade authFacade;

    public UserDetails loadUserByUsername(String email, String type) throws UsernameNotFoundException {
        return new AuthDetails(email, authFacade.queryUserRole(email, type));
    }

}
