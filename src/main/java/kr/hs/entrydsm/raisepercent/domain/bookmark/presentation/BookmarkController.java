package kr.hs.entrydsm.raisepercent.domain.bookmark.presentation;

import kr.hs.entrydsm.raisepercent.domain.bookmark.service.RegisterBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@RequestMapping("/bookmarks")
@RestController
public class BookmarkController {

    private final RegisterBookmarkService registerBookmarkService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{student-email}")
    public void registerBookmark(@PathVariable("student-email") String studentEmail) {
        registerBookmarkService.execute(studentEmail);
    }

}