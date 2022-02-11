package kr.hs.entrydsm.raisepercent.domain.teacher.presentation;

import kr.hs.entrydsm.raisepercent.domain.teacher.service.TeacherGoogleAuthService;
import kr.hs.entrydsm.raisepercent.global.util.auth.service.QueryGoogleAuthLinkService;
import kr.hs.entrydsm.raisepercent.domain.user.presentation.dto.request.CodeRequest;
import kr.hs.entrydsm.raisepercent.global.security.jwt.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/teachers")
@RestController
public class TeacherController {

    private final TeacherGoogleAuthService teacherGoogleAuthService;
    private final QueryGoogleAuthLinkService queryGoogleAuthLinkService;

    @GetMapping("/auth")
    public String queryGoogleAuthLink() {
        return queryGoogleAuthLinkService.execute();
    }

    @PostMapping("/auth")
    public ResponseEntity<TokenResponse> googleAuthLogin(@RequestBody CodeRequest codeRequest) {
        return teacherGoogleAuthService.execute(codeRequest);
    }

}
