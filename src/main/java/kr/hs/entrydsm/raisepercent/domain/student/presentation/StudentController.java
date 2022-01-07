package kr.hs.entrydsm.raisepercent.domain.student.presentation;

import kr.hs.entrydsm.raisepercent.domain.student.presentation.dto.response.ProfileResponse;
import kr.hs.entrydsm.raisepercent.domain.student.service.QueryStudentProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/students")
@RestController
public class StudentController {

    private final QueryStudentProfileService queryStudentProfileService;

    @GetMapping("/{student-email}")
    public ProfileResponse queryStudentProfile(@PathVariable("student-email") String email) {
        return queryStudentProfileService.execute(email);
    }

}
