package kr.hs.entrydsm.raisepercent.domain.company.exception;

import kr.hs.entrydsm.raisepercent.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyNotFoundExceptionTest {
    @Test
    void 회사_존재_예외() {
        assertEquals(ErrorCode.COMPANY_NOT_FOUND, CompanyNotFoundException.EXCEPTION.getErrorCode());
    }
}
