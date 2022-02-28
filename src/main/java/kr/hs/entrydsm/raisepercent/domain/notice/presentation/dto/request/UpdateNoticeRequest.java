package kr.hs.entrydsm.raisepercent.domain.notice.presentation.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class UpdateNoticeRequest {

    @NotBlank(message = "title은 비어있으면 안됩니다.")
    @Length(max = 20, message = "제목은 20자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "content는 비어있으면 안됩니다.")
    @Length(max = 255, message = "content는 255 이하여야 합니다.")
    private String content;

}
