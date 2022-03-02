package kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DsmAuthTokenRequest {

    private final String clientId;
    private final String clientSecret;
    private final String code;

}
