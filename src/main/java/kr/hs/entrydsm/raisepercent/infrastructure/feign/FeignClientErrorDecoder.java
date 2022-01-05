package kr.hs.entrydsm.raisepercent.infrastructure.feign;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.exception.OtherBadRequestException;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.exception.OtherExpiredTokenException;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.exception.OtherForbiddenException;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.exception.OtherUnAuthorizedException;

public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        if(response.status() >= 400) {
            switch (response.status()){
                case 401:
                    throw OtherUnAuthorizedException.EXCEPTION;
                case 403:
                    throw OtherForbiddenException.EXCEPTION;
                case 419:
                    throw OtherExpiredTokenException.EXCEPTION;
                default:
                    throw OtherBadRequestException.EXCEPTION;
            }
        }

        return FeignException.errorStatus(methodKey, response);
    }

}
