package kr.hs.entrydsm.raisepercent.domain.teacher.code.service;

import kr.hs.entrydsm.raisepercent.domain.teacher.code.domain.Code;
import kr.hs.entrydsm.raisepercent.domain.teacher.code.domain.repositories.CodeRepository;
import kr.hs.entrydsm.raisepercent.domain.teacher.code.facade.CodeFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CodeReissueServiceTest {

    private final String codeId = "asfdasfdasdfsadf";

    private final String codeValue = "asfsadfsdfsdafdsfsdafsadf";

    @Mock
    private CodeFacade codeFacade;

    @Mock
    private CodeRepository codeRepository;

    @InjectMocks
    private CodeReissueService codeReissueService;

    @Test
    void 코드_재발급() {
        //given
        ReflectionTestUtils.setField(codeReissueService, "id", codeId);
        Code code = Code.builder().value(codeValue).build();

        given(codeFacade.getRandomCode())
                .willReturn(codeValue);

        given(codeRepository.findById(codeId))
                .willReturn(Optional.of(code));

        given(codeRepository.save(any()))
                .willReturn(code);

        //when
        String retCode = codeReissueService.execute();

        //then
        assertThat(codeValue).isEqualTo(retCode);
    }

    @Test
    void 코드_생성() {
        //given
        given(codeFacade.getRandomCode())
                .willReturn(codeValue);

        given(codeRepository.findById(any()))
                .willReturn(Optional.empty());

        given(codeRepository.save(any()))
                .willReturn(Code.builder().value(codeValue).build());

        //when
        String retCode = codeReissueService.execute();

        //then
        assertThat(codeValue).isEqualTo(retCode);
    }

}
