package kr.hs.entrydsm.raisepercent.domain.code.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeTest {

    private static final String id = "Test id";

    private static final String value = "12345";

    private static final Code code = Code.builder()
            .id(id)
            .value(value)
            .build();

    @Test
    void 코드_객체_생성() {
        Code code = new Code();
        assertNull(code.getId());
        assertNull(code.getValue());
    }

    @Test
    void 코드_아이디_가져오기() {
        assertEquals(id, code.getId());
    }

    @Test
    void 코드_값_가져오기() {
        assertEquals(value, code.getValue());
    }

    @Test
    void 코드_업데이트() {
        String updatedValue = "54321";
        code.updateCode(updatedValue);
        assertEquals(updatedValue, code.getValue());
    }

}