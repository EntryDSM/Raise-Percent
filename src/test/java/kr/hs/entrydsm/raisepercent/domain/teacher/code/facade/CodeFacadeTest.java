package kr.hs.entrydsm.raisepercent.domain.teacher.code.facade;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CodeFacadeTest {

    private final CodeFacade codeFacade = new CodeFacade();

    @Test
    void 코드_생성() {
        int num = 5;
        String code = codeFacade.getRandomCode();

        assertThat(code.length()).isEqualTo(num);
    }

}
