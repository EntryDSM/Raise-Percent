package kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserAuthLinkResponse {
    private final String link;
}
