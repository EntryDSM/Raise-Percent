package kr.hs.entrydsm.raisepercent.global.util;

import kr.hs.entrydsm.raisepercent.global.exception.InvalidUUIDValueException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UUIDUtilTest {

    @Test
    void 변환() {
        UUID uuid = UUID.randomUUID();
        assertEquals(uuid, UUIDUtil.convertToUUID(uuid.toString()));
    }

    @Test
    void 잘못된_값() {
        assertThrows(InvalidUUIDValueException.class, () -> UUIDUtil.convertToUUID("test"));
    }

    @Test
    void 생성() throws NoSuchMethodException {
        Constructor<UUIDUtil> constructor = UUIDUtil.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertEquals("Utility class", exception.getCause().getMessage());
    }

}