package kr.hs.entrydsm.raisepercent.domain.student.presentation;

import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.request.CodeRequest;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.request.RegisterTagRequest;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.request.UpdatePositionRequest;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response.BookmarkListResponse;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response.ProfileResponse;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response.TokenResponse;
import kr.hs.entrydsm.raisepercent.domain.student.service.QueryBookmarkService;
import kr.hs.entrydsm.raisepercent.domain.student.service.QueryStudentProfileService;
import kr.hs.entrydsm.raisepercent.domain.student.service.QueryUserAuthLinkService;
import kr.hs.entrydsm.raisepercent.domain.student.service.RegisterTagService;
import kr.hs.entrydsm.raisepercent.domain.student.service.UpdatePositionService;
import kr.hs.entrydsm.raisepercent.domain.student.service.StudentAuthService;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response.UserAuthLinkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/students")
@RestController
public class StudentController {

    private final QueryStudentProfileService queryStudentProfileService;
    private final RegisterTagService registerTagService;
    private final UpdatePositionService updatePositionService;
    private final QueryBookmarkService queryBookmarkService;
    private final StudentAuthService studentAuthService;
    private final QueryUserAuthLinkService queryUserAuthLinkService;

    @GetMapping("/{student-email}")
    public ProfileResponse queryStudentProfile(@PathVariable("student-email") String email) {
        return queryStudentProfileService.execute(email);
    }

    @PostMapping("/tag")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerTag(@RequestBody @Valid RegisterTagRequest request) {
        registerTagService.execute(request);
    }

    @PatchMapping("/position")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePosition(@RequestBody @Valid UpdatePositionRequest request) {
        updatePositionService.execute(request);
    }

    @GetMapping("/bookmark")
    public BookmarkListResponse queryBookmark() {
        return queryBookmarkService.execute();
    }
  
    @PostMapping("/auth")
    public ResponseEntity<TokenResponse> userAuth(@RequestBody @Valid CodeRequest request) {
        return studentAuthService.execute(request.getCode());
    }
  
    @GetMapping("/auth")
    public UserAuthLinkResponse queryUserAuthLink() {
        return queryUserAuthLinkService.execute();
    }

}
