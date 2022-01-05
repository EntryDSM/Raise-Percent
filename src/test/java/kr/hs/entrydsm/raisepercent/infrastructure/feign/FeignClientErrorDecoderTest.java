package kr.hs.entrydsm.raisepercent.infrastructure.feign;

import feign.Response;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.exception.OtherBadRequestException;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.exception.OtherExpiredTokenException;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.exception.OtherForbiddenException;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.exception.OtherUnAuthorizedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FeignClientErrorDecoderTest {

    private static final String methodKey = "entrydsmraisepercent";

    private static final Response response = mock(Response.class);

    private static final FeignClientErrorDecoder errorDecoder = mock(FeignClientErrorDecoder.class);

    @Test
    void 다른서버_잘못된_요청() {
        int status = 400;

        when(response.status())
                .thenReturn(status);

        assertThrows(OtherBadRequestException.class, () -> errorDecoder.decode(methodKey, response));
    }

    @Test
    void 다른서버_인증되지않음() {
        int status = 401;

        when(response.status())
                .thenReturn(status);

        assertThrows(OtherUnAuthorizedException.class, () -> errorDecoder.decode(methodKey, response));
    }

    @Test
    void 다른서버_권한없음() {
        int status = 403;

        when(response.status())
                .thenReturn(status);

        assertThrows(OtherForbiddenException.class, () -> errorDecoder.decode(methodKey, response));
    }

    @Test
    void 다른서버_토큰_만료() {
        int status = 419;

        when(response.status())
                .thenReturn(status);

        assertThrows(OtherExpiredTokenException.class, () -> errorDecoder.decode(methodKey, response));
    }

}
