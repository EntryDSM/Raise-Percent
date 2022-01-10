package kr.hs.entrydsm.raisepercent.domain.code.service;

import kr.hs.entrydsm.raisepercent.domain.code.domain.Code;
import kr.hs.entrydsm.raisepercent.domain.code.domain.repositories.CodeRepository;
import kr.hs.entrydsm.raisepercent.domain.code.facade.CodeFacade;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CodeReissueServiceTest {

    private static final String codeId = "asfdasfdasdfsadf";

    private static final String codeValue = "asfsadfsdfsdafdsfsdafsadf";

    private static final CodeFacade codeFacade = mock(CodeFacade.class);

    private static final CodeRepository codeRepository = mock(CodeRepository.class);

    private static final CodeReissueService codeReissueService = new CodeReissueService(codeFacade, codeRepository);

    @Test
    void 코드_재발급() {
        ReflectionTestUtils.setField(codeReissueService, "id", codeId);
        Code code = Code.builder().value(codeValue).build();

        when(codeFacade.getRandomCode())
                .thenReturn(codeValue);

        when(codeRepository.findById(codeId))
                .thenReturn(Optional.of(code));

        when(codeRepository.save(any()))
                .thenReturn(code);

        String retCode = codeReissueService.execute();

        assertEquals(codeValue, retCode);
    }

    @Test
    void 코드_생성() {
        when(codeFacade.getRandomCode())
                .thenReturn(codeValue);

        when(codeRepository.findById(codeId))
                .thenReturn(Optional.empty());

        when(codeRepository.save(any()))
                .thenReturn(Code.builder().value(codeValue).build());

        String retCode = codeReissueService.execute();

        assertEquals(codeValue, retCode);
    }

}
