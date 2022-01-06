package kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UpdateDeviceTokenRequest {

    @NotBlank(message = "type은 비어있으면 안됩니다.")
    private String type;

    @NotBlank(message = "device_token은 비어있으면 안됩니다.")
    private String deviceToken;

}
