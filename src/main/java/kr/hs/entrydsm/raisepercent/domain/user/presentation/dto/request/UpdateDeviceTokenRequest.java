package kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UpdateDeviceTokenRequest {

    @NotBlank(message = "type은 비어있으면 안됩니다.")
    private String type;

    @NotBlank(message = "device_token은 비어있으면 안됩니다.")
    @Size(max = 255, message = "device_token은 255자를 넘어서는 안됩니다")
    private String deviceToken;

}
