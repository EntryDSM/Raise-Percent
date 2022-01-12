package kr.hs.entrydsm.raisepercent.domain.feedback.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UpdateFeedbackRequest {

    @NotBlank(message = "content는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 1, max = 200, message = "content는 200글자 미만이어야 합니다.")
    private String content;

}
