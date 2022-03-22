package kr.hs.entrydsm.raisepercent.domain.student.service;

import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response.UserAuthLinkResponse;
import kr.hs.entrydsm.raisepercent.global.properties.DsmAuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryUserAuthLinkService {

    private final DsmAuthProperties dsmAuthProperties;

    public UserAuthLinkResponse execute() {
        return UserAuthLinkResponse.builder()
                .link(dsmAuthProperties.getBaseUrl() + String.format(dsmAuthProperties.getFormat(),
                        dsmAuthProperties.getRedirectUrl(), dsmAuthProperties.getClientId()))
                .build();
    }

}
