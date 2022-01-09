package kr.hs.entrydsm.raisepercent.domain.user.facade;

import java.util.Optional;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserFacadeTest {

	private static final AuthFacade authFacade = mock(AuthFacade.class);

	private static final UserRepository userRepository = mock(UserRepository.class);

	private static final UserFacade userFacade = new UserFacade(authFacade, userRepository);

	@Test
	void 유저_객체_가져오기() {
		final String username = "test@dsm.hs.kr";
		User user = User.builder().build();

		when(authFacade.getCurrentDetails())
			.thenReturn(new AuthDetails(username, Type.TEACHER));
		when(userRepository.findById(any()))
			.thenReturn(Optional.ofNullable(user));

		assertEquals(user, userFacade.getCurrentUser());
	}
}
