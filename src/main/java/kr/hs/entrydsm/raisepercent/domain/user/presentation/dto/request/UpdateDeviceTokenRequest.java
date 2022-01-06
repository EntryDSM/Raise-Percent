package kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class UpdateDeviceTokenRequest {

    @NotEmpty(message = "type은 비어있으면 안됩니다.")
    private String type;

    @NotEmpty(message = "device_token은 비어있으면 안됩니다.")
    private String deviceToken;

}
