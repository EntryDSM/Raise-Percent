package kr.hs.entrydsm.raisepercent.global.facade;

import kr.hs.entrydsm.raisepercent.global.exception.CredentialsNotFoundException;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthFacade {

	public AuthDetails getCurrentDetails() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(principal instanceof AuthDetails)) {
			throw CredentialsNotFoundException.EXCEPTION;
		}
		return (AuthDetails) principal;
	}
}
