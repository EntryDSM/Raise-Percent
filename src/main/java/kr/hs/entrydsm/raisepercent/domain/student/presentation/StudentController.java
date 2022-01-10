package kr.hs.entrydsm.raisepercent.domain.student.presentation;

import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.request.RegisterTagRequest;
import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response.ProfileResponse;
import kr.hs.entrydsm.raisepercent.domain.student.service.QueryStudentProfileService;
import kr.hs.entrydsm.raisepercent.domain.student.service.RegisterTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/{student-email}")
    public ProfileResponse queryStudentProfile(@PathVariable("student-email") String email) {
        return queryStudentProfileService.execute(email);
    }

    @PostMapping("/tag")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerTag(@RequestBody @Valid RegisterTagRequest request) {
        registerTagService.execute(request);
    }

}
