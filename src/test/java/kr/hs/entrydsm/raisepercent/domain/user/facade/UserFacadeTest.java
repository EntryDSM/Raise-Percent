package kr.hs.entrydsm.raisepercent.domain.user.facade;

import java.util.Optional;
import kr.hs.entrydsm.raisepercent.domain.user.domain.User;
import kr.hs.entrydsm.raisepercent.domain.user.domain.repositories.UserRepository;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserFacadeTest {

	@Mock
	private AuthFacade authFacade;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserFacade userFacade;

	@Test
	void 유저_객체_가져오기() {
		//given
		final String username = "test@dsm.hs.kr";
		User user = User.builder().build();

		given(authFacade.getCurrentDetails())
			.willReturn(new AuthDetails(username, Type.TEACHER));
		given(userRepository.findById(any()))
			.willReturn(Optional.ofNullable(user));

		//when then
		assertEquals(user, userFacade.getCurrentUser());
	}
}
