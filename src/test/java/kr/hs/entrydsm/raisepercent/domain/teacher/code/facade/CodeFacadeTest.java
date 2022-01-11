package kr.hs.entrydsm.raisepercent.domain.teacher.code.facade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodeFacadeTest {

    private static final CodeFacade codeFacade = new CodeFacade();

    @Test
    void 코드_생성() {
        int num = 5;
        String code = codeFacade.getRandomCode();

        assertEquals(code.length(), num);
    }

}
