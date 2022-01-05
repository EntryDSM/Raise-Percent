package kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UpdateInformationRequest {

    @Email(message = "잘못된 이메일입니다.")
    private String contactEmail;

    @Pattern(regexp = "^\\\\d{3}\\\\d{3,4}\\\\d{4}$", message = "잘못된 전화번호입니다.")
    private String contactTel;

}
