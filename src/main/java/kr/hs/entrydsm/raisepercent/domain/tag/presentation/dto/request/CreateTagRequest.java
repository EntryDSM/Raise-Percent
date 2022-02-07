package kr.hs.entrydsm.raisepercent.domain.tag.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CreateTagRequest {

    @NotBlank(message = "name은 Null, 공백, 띄어쓰기가 허용되지 않습니다.")
    private String name;

}
