package kr.hs.entrydsm.raisepercent.domain.teacher.service;

import kr.hs.entrydsm.raisepercent.global.properties.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryGoogleAuthLinkService {

    private final AuthProperties authProperties;

    public String execute() {
        return authProperties.getBaseUrl() + "?client-id=" + authProperties.getClientId()
                + "&redirect_uri=" + authProperties.getRedirectUrl() + "&response_type=code&"
                + "scope=https://www.googleapis.com/auth/userinfo.email";
    }

}
