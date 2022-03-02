package kr.hs.entrydsm.raisepercent.infrastructure.feign.client;

import kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.request.DsmAuthTokenRequest;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.response.DsmAuthTokenResponse;
import kr.hs.entrydsm.raisepercent.infrastructure.feign.dto.response.InformationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "DsmAuthClient", url = "${auth.dsm.request-url}")
public interface DsmAuth {
    @PostMapping("/dsmauth/token")
    DsmAuthTokenResponse getToken(DsmAuthTokenRequest request);

    @GetMapping("v1/info/basic")
    InformationResponse getInformation(@RequestHeader("Authorization") String value);

}
