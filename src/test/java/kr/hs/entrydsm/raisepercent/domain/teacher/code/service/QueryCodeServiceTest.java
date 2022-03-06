package kr.hs.entrydsm.raisepercent.domain.teacher.code.service;

import kr.hs.entrydsm.raisepercent.domain.teacher.code.domain.Code;
import kr.hs.entrydsm.raisepercent.domain.teacher.code.domain.repositories.CodeRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.code.exception.CodeNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class QueryCodeServiceTest {

    private final String codeId = "TEACHERVERIFICATIONCODE";

    private final CodeRepository codeRepository = mock(CodeRepository.class);

    private final QueryCodeService queryCodeService = new QueryCodeService(codeRepository);

    @Test
    void 코드_출력() {
        //given
        ReflectionTestUtils.setField(queryCodeService, "id", codeId);
        Code code = Code.builder().value("codeValue").build();

        given(codeRepository.findById(codeId))
                .willReturn(Optional.of(code));

        //when
        String retCode = queryCodeService.execute();

        //then
        assertEquals(code.getValue(), retCode);
    }

    @Test
    void 코드_없음_예외() {
        //given
        given(codeRepository.findById(codeId))
                .willReturn(Optional.empty());

        //when then
        assertThrows(CodeNotFoundException.class, queryCodeService::execute);
    }

}
