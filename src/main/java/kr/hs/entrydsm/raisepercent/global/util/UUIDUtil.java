package kr.hs.entrydsm.raisepercent.global.util;

import kr.hs.entrydsm.raisepercent.global.exception.InvalidUUIDValueException;

import java.util.UUID;

public class UUIDUtil {

    private UUIDUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static UUID convertToUUID(String value) {
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw InvalidUUIDValueException.EXCEPTION;
        }
    }

}
