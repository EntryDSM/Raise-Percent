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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class HrFacadeTest {
    String email = "test@google.com";
    private static final AuthFacade authFacade = mock(AuthFacade.class);
    private static final HrRepository hrRepository = mock(HrRepository.class);
    private static final HrFacade hrFacade = new HrFacade(hrRepository,authFacade);

    @Test
    void 인사담당자_정보_가져오기() {
        Hr hr = Hr.builder().build();

        when(authFacade.getCurrentDetails())
                .thenReturn(new AuthDetails(email, Type.JUNIOR));

        when(authFacade.getCurrentDetails())
                .thenReturn(new AuthDetails(email, Type.SENIOR));

        when(hrRepository.findById(email))
                .thenReturn(Optional.of(hr));

        assertEquals(hr,hrFacade.getHr());
    }

    @Test
    void 인사담당자_예외() {
        when(authFacade.getCurrentDetails())
                .thenReturn(new AuthDetails(email,Type.SENIOR));

        when(authFacade.getCurrentDetails())
                .thenReturn(new AuthDetails(email,Type.JUNIOR));

        when(hrRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(HrNotFoundException.class, hrFacade::getHr);

    }
}