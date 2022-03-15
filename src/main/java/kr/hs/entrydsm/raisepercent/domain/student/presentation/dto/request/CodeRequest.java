package kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CodeRequest {

    @NotBlank(message = "code는 비어있으면 안됩니다.")
    private String code;

}
