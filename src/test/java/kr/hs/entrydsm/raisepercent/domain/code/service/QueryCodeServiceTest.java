package kr.hs.entrydsm.raisepercent.domain.code.service;

import kr.hs.entrydsm.raisepercent.domain.code.domain.Code;
import kr.hs.entrydsm.raisepercent.domain.code.domain.repositories.CodeRepository;
import kr.hs.entrydsm.raisepercent.global.exception.CodeNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QueryCodeServiceTest {

    private static final String codeId = "TEACHERVERIFICATIONCODE";

    private static final CodeRepository codeRepository = mock(CodeRepository.class);

    private static final QueryCodeService queryCodeService = new QueryCodeService(codeRepository);

    @Test
    void 코드_출력() {
        Code code = Code.builder()
                .id(codeId)
                .value("asd2f")
                .build();

        when(codeRepository.findById(codeId))
                .thenReturn(Optional.of(code));

        String retCode = queryCodeService.execute();

        assertEquals(code.getValue(), retCode);
    }

    @Test
    void 코드_없음_예외() {
        when(codeRepository.findById(codeId))
                .thenReturn(Optional.empty());

        assertThrows(CodeNotFoundException.class, queryCodeService::execute);
    }

}
