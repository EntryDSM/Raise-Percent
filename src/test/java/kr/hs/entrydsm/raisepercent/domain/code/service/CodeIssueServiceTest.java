package kr.hs.entrydsm.raisepercent.domain.code.service;

import kr.hs.entrydsm.raisepercent.domain.code.domain.Code;
import kr.hs.entrydsm.raisepercent.domain.code.domain.repositories.CodeRepository;
import kr.hs.entrydsm.raisepercent.domain.code.facade.CodeFacade;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CodeIssueServiceTest {

    private static final String codeId = "TEACHERVERIFICATIONCODE";

    private static final CodeFacade codeFacade = mock(CodeFacade.class);

    private static final CodeRepository codeRepository = mock(CodeRepository.class);

    private static final CodeIssueService codeIssueService = new CodeIssueService(codeFacade, codeRepository);

    @Test
    void 코드_출력() {
        Code code = Code.builder()
                .id(codeId)
                .value("asd2f")
                .build();

        when(codeRepository.findById(codeId))
                .thenReturn(Optional.of(code));

        String retCode = codeIssueService.execute();

        assertEquals(code.getValue(), retCode);
    }

    @Test
    void 코드_발급() {
        when(codeRepository.findById(codeId))
                .thenReturn(Optional.empty());

        String code = codeFacade.getRandomCode();

        when(codeFacade.getRandomCode())
                .thenReturn(code);

        String saveCode = codeIssueService.execute();

        verify(codeRepository, times(1)).save(any());

        assertEquals(code, saveCode);
    }

}
