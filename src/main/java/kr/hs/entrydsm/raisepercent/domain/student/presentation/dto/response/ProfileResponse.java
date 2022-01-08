package kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProfileResponse {

    private final String name;
    private final String contactEmail;
    private final String contactTel;
    private final String position;
    private final List<String> tags;

}
