package kr.hs.entrydsm.raisepercent.global.feign.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GoogleCodeRequest {

    private final String code;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String grantType = "authorization_code";

    @Builder
    public GoogleCodeRequest(String code, String clientId, String clientSecret, String redirectUri) {
        this.code = code;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }

}
