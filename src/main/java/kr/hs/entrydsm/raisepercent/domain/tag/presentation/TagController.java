package kr.hs.entrydsm.raisepercent.domain.tag.presentation;

import kr.hs.entrydsm.raisepercent.domain.tag.presentation.dto.request.CreateTagRequest;
import kr.hs.entrydsm.raisepercent.domain.tag.presentation.dto.response.ShowTagListResponse;
import kr.hs.entrydsm.raisepercent.domain.tag.service.CreateTagService;
import kr.hs.entrydsm.raisepercent.domain.tag.service.ShowTagListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/tags")
@RestController
public class TagController {

    private final ShowTagListService showTagListService;
    private final CreateTagService createTagService;

    @GetMapping
    public ShowTagListResponse showTagList(@RequestParam("name") String name) {
        return showTagListService.execute(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createTag(@RequestBody @Valid CreateTagRequest request) {
        createTagService.execute(request);
    }

}
