package kr.hs.entrydsm.raisepercent.domain.hr.facade;

import kr.hs.entrydsm.raisepercent.domain.hr.domain.Hr;
import kr.hs.entrydsm.raisepercent.domain.hr.domain.repositories.HrRepository;
import kr.hs.entrydsm.raisepercent.global.exception.HrNotFoundException;
import kr.hs.entrydsm.raisepercent.global.facade.AuthFacade;
import kr.hs.entrydsm.raisepercent.global.security.auth.AuthDetails;
import kr.hs.entrydsm.raisepercent.global.security.auth.Type;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class HrFacadeTest {

    private final String email = "test@google.com";

    @Mock
    private AuthFacade authFacade;

    @Mock
    private HrRepository hrRepository;

    @InjectMocks
    private HrFacade hrFacade;

    @Test
    void 인사담당자_정보_가져오기() {
        //given
        Hr hr = Hr.builder().build();

        given(authFacade.getCurrentDetails())
                .willReturn(new AuthDetails(email, Type.JUNIOR));

        given(authFacade.getCurrentDetails())
                .willReturn(new AuthDetails(email, Type.SENIOR));

        given(hrRepository.findById(email))
                .willReturn(Optional.of(hr));

        //when then
        assertEquals(hr, hrFacade.getHr());
    }

    @Test
    void 인사담당자_예외() {
        //given
        given(authFacade.getCurrentDetails())
                .willReturn(new AuthDetails(email,Type.SENIOR));

        given(authFacade.getCurrentDetails())
                .willReturn(new AuthDetails(email,Type.JUNIOR));

        given(hrRepository.findById(anyString()))
                .willReturn(Optional.empty());

        //when then
        assertThrows(HrNotFoundException.class, hrFacade::getHr);

    }
}