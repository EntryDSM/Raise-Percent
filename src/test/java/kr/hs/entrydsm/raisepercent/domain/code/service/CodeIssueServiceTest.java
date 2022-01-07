package kr.hs.entrydsm.raisepercent.domain.code.service;

import kr.hs.entrydsm.raisepercent.domain.code.domain.Code;
import kr.hs.entrydsm.raisepercent.domain.code.domain.repositories.CodeRepository;
import kr.hs.entrydsm.raisepercent.domain.code.facade.CodeAlreadyExistsException;
import kr.hs.entrydsm.raisepercent.domain.code.facade.CodeFacade;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CodeIssueServiceTest {

    private static final String codeId = "TEACHERVERIFICATIONCODE";

    private static final CodeFacade codeFacade = mock(CodeFacade.class);

    private static final CodeRepository codeRepository = mock(CodeRepository.class);

    private static final CodeIssueService codeIssueService = new CodeIssueService(codeFacade, codeRepository);

    @Test
    void 코드_발급() {
        String code = codeFacade.getCode();

        when(codeRepository.findById(codeId))
                .thenReturn(Optional.empty());

        when(codeFacade.getCode())
                .thenReturn(code);

        String saveCode = codeIssueService.execute();

        verify(codeRepository, times(1)).save(any());

        assertEquals(code, saveCode);
    }

    @Test
    void 코드_이미_존재함() {
        when(codeRepository.findById(codeId))
                .thenReturn(Optional.of(Code.builder().build()));

        assertThrows(CodeAlreadyExistsException.class, codeIssueService::execute);
    }

}
