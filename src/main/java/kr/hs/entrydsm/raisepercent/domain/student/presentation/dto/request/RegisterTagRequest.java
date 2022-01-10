package kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterTagRequest {

    private List<@NotBlank(message = "tag_id는 비어있으면 안됩니다.") String> tagId;

}
