package kr.hs.entrydsm.raisepercent.domain.code.service;

import kr.hs.entrydsm.raisepercent.domain.code.domain.Code;
import kr.hs.entrydsm.raisepercent.domain.code.domain.repositories.CodeRepository;
import kr.hs.entrydsm.raisepercent.domain.code.facade.CodeFacade;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CodeReissueServiceTest {

    private static final String codeId = "asfdasfdasdfsadf";

    private static final CodeFacade codeFacade = mock(CodeFacade.class);

    private static final CodeRepository codeRepository = mock(CodeRepository.class);

    private static final CodeReissueService codeReissueService = new CodeReissueService(codeFacade, codeRepository);

    @Test
    void 코드_재발급() {
        Code code = Code.builder().build();

        when(codeRepository.findById(codeId))
                .thenReturn(Optional.of(code));

        String retCode = codeReissueService.execute();

        assertEquals(code.getValue(), retCode);
    }

    @Test
    void 코드_생성() {
        Code code = Code.builder().build();

        when(codeRepository.findById(codeId))
                .thenReturn(Optional.empty());

        String retCode = codeReissueService.execute();

        assertEquals(code.getValue(), retCode);

        verify(codeRepository, times(1)).save(any());
    }

}
