package kr.hs.entrydsm.raisepercent.domain.teacher.presentation;

import kr.hs.entrydsm.raisepercent.domain.teacher.service.QueryGoogleAuthLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/teacher")
@RestController
public class TeacherController {

    private final QueryGoogleAuthLinkService queryGoogleAuthLinkService;

    @GetMapping("/auth")
    public String queryGoogleAuthLink() {
        return queryGoogleAuthLinkService.execute();
    }

}
