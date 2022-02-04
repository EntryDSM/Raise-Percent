package kr.hs.entrydsm.raisepercent.global.util.auth.service;

import kr.hs.entrydsm.raisepercent.global.properties.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryGoogleAuthLinkService {

    private static final String url = "%s?client_id=%s&redirect_uri=%s&response_type=code"
            + "&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";

    private final AuthProperties authProperties;

    public String execute() {
        return String.format(url,
                authProperties.getBaseUrl(),
                authProperties.getClientId(),
                authProperties.getRedirectUrl());
    }

}
