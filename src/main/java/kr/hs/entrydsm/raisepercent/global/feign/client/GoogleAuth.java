package kr.hs.entrydsm.raisepercent.global.feign.client;

import kr.hs.entrydsm.raisepercent.global.feign.dto.request.GoogleCodeRequest;
import kr.hs.entrydsm.raisepercent.global.feign.dto.response.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GoogleAuthClient", url = "https://oauth2.googleapis.com/token")
public interface GoogleAuth {

    @PostMapping
    TokenResponse googleAuth(GoogleCodeRequest googleCodeRequest);

}
