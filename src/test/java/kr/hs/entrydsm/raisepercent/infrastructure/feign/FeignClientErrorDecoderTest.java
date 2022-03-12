package kr.hs.entrydsm.raisepercent.infrastructure.feign;

import feign.Response;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.exception.OtherBadRequestException;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.exception.OtherExpiredTokenException;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.exception.OtherForbiddenException;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.exception.OtherUnAuthorizedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FeignClientErrorDecoderTest {

    @Mock
    private Response response;

    @InjectMocks
    private FeignClientErrorDecoder feignClientErrorDecoder;

    private Exception decode() {
        String methodKey = "methodKey";
        return feignClientErrorDecoder.decode(methodKey, response);
    }

    @Test
    void 다른서버_잘못된_요청() {
        int status = 400;
        given(response.status())
                .willReturn(status);

        assertThrows(OtherBadRequestException.class, this::decode);
    }

    @Test
    void 다른서버_인증되지_않음() {
        int status = 401;
        given(response.status())
                .willReturn(status);

        assertThrows(OtherUnAuthorizedException.class, this::decode);
    }

    @Test
    void 다른서버_권한_없음() {
        int status = 403;
        given(response.status())
                .willReturn(status);

        assertThrows(OtherForbiddenException.class, this::decode);
    }

    @Test
    void 다른서버_토큰_만료됨() {
        int status = 419;
        given(response.status())
                .willReturn(status);

        assertThrows(OtherExpiredTokenException.class, this::decode);
    }

}
