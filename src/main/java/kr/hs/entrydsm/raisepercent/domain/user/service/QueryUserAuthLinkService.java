package kr.hs.entrydsm.raisepercent.domain.user.service;

import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.response.UserAuthLinkResponse;
import kr.hs.entrydsm.raisepercent.global.properties.DsmAuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryUserAuthLinkService {

    private final DsmAuthProperties dsmAuthProperties;

    public UserAuthLinkResponse execute() {
        return UserAuthLinkResponse.builder().link(dsmAuthProperties.getBaseUrl() + "external/login?redirect_url=" +
                dsmAuthProperties.getRedirectUrl() + "&client_id=" + dsmAuthProperties.getClientId()).build();
    }

}
