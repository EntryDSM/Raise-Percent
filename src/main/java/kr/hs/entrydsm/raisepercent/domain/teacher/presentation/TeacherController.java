package kr.hs.entrydsm.raisepercent.domain.teacher.presentation;

import kr.hs.entrydsm.raisepercent.domain.code.service.QueryCodeService;
import kr.hs.entrydsm.raisepercent.domain.code.service.CodeReissueService;
import kr.hs.entrydsm.raisepercent.domain.teacher.service.GoogleAuthService;
import kr.hs.entrydsm.raisepercent.domain.teacher.service.QueryGoogleAuthLinkService;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.CodeRequest;
import kr.hs.entrydsm.raisepercent.global.security.jwt.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/teachers")
@RestController
public class TeacherController {

    private final QueryCodeService queryCodeService;
    private final CodeReissueService codeReissueService;
    private final GoogleAuthService googleAuthService;
    private final QueryGoogleAuthLinkService queryGoogleAuthLinkService;

    @GetMapping("/auth")
    public String queryGoogleAuthLink() {
        return queryGoogleAuthLinkService.execute();
    }

    @PostMapping("/auth")
    public ResponseEntity<TokenResponse> googleAuthLogin(@RequestBody CodeRequest codeRequest) {
        return googleAuthService.execute(codeRequest);
    }

    @GetMapping("/code")
    public String codeIssue() {
        return queryCodeService.execute();
    }

    @PutMapping("/reissue")
    public String codeReissue() {
        return codeReissueService.execute();
    }

}
