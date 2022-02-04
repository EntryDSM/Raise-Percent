package kr.hs.entrydsm.raisepercent.domain.hr.presentation;

import kr.hs.entrydsm.raisepercent.global.util.auth.service.QueryGoogleAuthLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class HrController {
    private final QueryGoogleAuthLinkService queryGoogleAuthLinkService;

    @GetMapping("/auth")
    public String getLoginLink(){
        return queryGoogleAuthLinkService.execute();
    }

}
