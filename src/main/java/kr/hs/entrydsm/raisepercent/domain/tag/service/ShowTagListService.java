package kr.hs.entrydsm.raisepercent.domain.tag.service;

import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.TagRepository;
import kr.hs.entrydsm.raisepercent.domain.tag.presentation.dto.response.ShowTagListResponse;
import kr.hs.entrydsm.raisepercent.domain.tag.presentation.dto.response.TagListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ShowTagListService {

    private final TagRepository tagRepository;

    public ShowTagListResponse execute() {
        List<TagListResponse> tagList = tagRepository.findAll()
                .stream().map(tag -> new TagListResponse(tag.getId(), tag.getName()))
                .collect(Collectors.toList());

        return new ShowTagListResponse(tagList);
    }

}
