package kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class RegistrationNoticeRequest {

    @NotBlank(message = "title은 비어있으면 안됩니다.")
    private String title;

    @NotBlank(message = "content는 비어있으면 안됩니다.")
    private String content;

    @NotBlank(message = "scope는 비어있으면 안됩니다.")
    private String scope;

}
