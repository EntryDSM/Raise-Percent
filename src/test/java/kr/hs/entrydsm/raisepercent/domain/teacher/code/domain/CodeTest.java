package kr.hs.entrydsm.raisepercent.domain.teacher.code.domain;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CodeTest {

    private final String id = "Test id";

    private final String value = "12345";

    private final Code code = Code.builder()
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
        assertThat(id).isEqualTo(code.getId());
    }

    @Test
    @Order(0)
    void 코드_값_가져오기() {
        assertThat(value).isEqualTo(code.getValue());
    }

    @Test
    void 코드_업데이트() {
        String updatedValue = "54321";
        code.updateCode(updatedValue);
        assertThat(updatedValue).isEqualTo(code.getValue());
    }

}