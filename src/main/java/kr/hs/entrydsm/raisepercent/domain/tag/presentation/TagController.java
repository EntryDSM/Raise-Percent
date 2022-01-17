package kr.hs.entrydsm.raisepercent.domain.tag.presentation;

import kr.hs.entrydsm.raisepercent.domain.tag.presentation.dto.response.ShowTagListResponse;
import kr.hs.entrydsm.raisepercent.domain.tag.service.ShowTagListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/tags")
@RestController
public class TagController {

    private final ShowTagListService showTagListService;

    @GetMapping
    public ShowTagListResponse showTagList() {
        return showTagListService.execute();
    }
}
