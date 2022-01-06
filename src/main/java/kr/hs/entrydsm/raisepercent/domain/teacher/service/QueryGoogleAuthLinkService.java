package kr.hs.entrydsm.raisepercent.domain.teacher.service;

import kr.hs.entrydsm.raisepercent.global.properties.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryGoogleAuthLinkService {

    private final AuthProperties authProperties;

    public String execute() {
        return String.format("%s?client-id=%s&redirect_uri=%s&response_type=code"
                        + "&scope=https://www.googleapis.com/auth/userinfo.email",
                authProperties.getBaseUrl(),
                authProperties.getClientId(),
                authProperties.getRedirectUrl());
    }

}
