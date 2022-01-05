package kr.hs.entrydsm.raisepercent.infrastructure.feign.client;

import kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.request.GoogleCodeRequest;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.response.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GoogleAuthClient", url = "https://oauth2.googleapis.com/token")
public interface GoogleAuth {

    @PostMapping
    TokenResponse googleAuth(GoogleCodeRequest googleCodeRequest);

}
