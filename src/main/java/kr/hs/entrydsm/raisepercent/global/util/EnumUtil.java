package kr.hs.entrydsm.raisepercent.global.util;

import kr.hs.entrydsm.raisepercent.global.exception.InvalidEnumValueException;

public class EnumUtil {

    private EnumUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static <T extends Enum<T>> T convertToEnum(String value, Class<T> enumClass) {
        if(value == null) {
            return null;
        }
        try {
            return Enum.valueOf(enumClass, value);
        } catch (IllegalArgumentException e) {
            throw InvalidEnumValueException.EXCEPTION;
        }
    }

}
