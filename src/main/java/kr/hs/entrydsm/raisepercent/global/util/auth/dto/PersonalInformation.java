package kr.hs.entrydsm.raisepercent.global.util.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PersonalInformation {
    private final String email;
    private final String name;
}
