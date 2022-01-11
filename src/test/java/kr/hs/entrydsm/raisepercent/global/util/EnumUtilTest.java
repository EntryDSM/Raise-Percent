package kr.hs.entrydsm.raisepercent.global.util;

import kr.hs.entrydsm.raisepercent.domain.student.domain.types.Position;
import kr.hs.entrydsm.raisepercent.global.exception.InvalidEnumValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumUtilTest {

    @Test
    void null값_파라미터() {
        assertNull(EnumUtil.convertToEnum(null, Position.class));
    }

    @Test
    void 변환() {
        assertEquals(Position.BACKEND, EnumUtil.convertToEnum("BACKEND", Position.class));
    }

    @Test
    void 잘못된_인수() {
        assertThrows(InvalidEnumValueException.class, () -> EnumUtil.convertToEnum("frontend", Position.class));
    }

}