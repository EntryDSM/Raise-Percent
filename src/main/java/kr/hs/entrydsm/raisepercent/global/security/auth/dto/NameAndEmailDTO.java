package kr.hs.entrydsm.raisepercent.global.security.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NameAndEmailDTO {
    private String name;
    private String email;
}
