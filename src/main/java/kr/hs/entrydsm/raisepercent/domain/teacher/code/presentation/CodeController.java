package kr.hs.entrydsm.raisepercent.domain.teacher.code.presentation;

import kr.hs.entrydsm.raisepercent.domain.teacher.code.service.CodeReissueService;
import kr.hs.entrydsm.raisepercent.domain.teacher.code.service.CodeVerificationService;
import kr.hs.entrydsm.raisepercent.domain.teacher.code.service.QueryCodeService;
import kr.hs.entrydsm.raisepercent.domain.teacher.code.presentation.dto.request.VerifyTeacherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController("/teachers")
public class CodeController {

    private final QueryCodeService queryCodeService;
    private final CodeReissueService codeReissueService;
    private final CodeVerificationService codeVerificationService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/auth")
    public void verifyTeacher(@RequestBody @Valid VerifyTeacherRequest request) {
        codeVerificationService.execute(request);
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
