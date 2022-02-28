package kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserAuthLinkResponse {
    private final String link;
}
