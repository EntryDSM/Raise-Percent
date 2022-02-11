package kr.hs.entrydsm.raisepercent.domain.hr.presentation;

import kr.hs.entrydsm.raisepercent.domain.hr.service.HrGoogleAuthService;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.CodeRequest;
import kr.hs.entrydsm.raisepercent.global.security.jwt.dto.TokenResponse;
import kr.hs.entrydsm.raisepercent.global.util.auth.service.QueryGoogleAuthLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/managers")
@RestController
public class HrController {
    private final QueryGoogleAuthLinkService queryGoogleAuthLinkService;
    private final HrGoogleAuthService hrGoogleAuthService;

    @GetMapping("/auth")
    public String getLoginLink(){
        return queryGoogleAuthLinkService.execute();
    }

    @PostMapping("/auth")
    public ResponseEntity<TokenResponse> googleAuthLogin(@RequestBody CodeRequest codeRequest) {
        return hrGoogleAuthService.execute(codeRequest);
    }

}
