package kr.hs.entrydsm.raisepercent.domain.tag.service;

import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.TagRepository;
import kr.hs.entrydsm.raisepercent.domain.tag.presentation.dto.response.ShowTagListResponse;
import kr.hs.entrydsm.raisepercent.domain.tag.presentation.dto.response.TagListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ShowTagListService {

    private final TagRepository tagRepository;

    public ShowTagListResponse execute() {
        return new ShowTagListResponse(tagRepository.findAll()
                .stream().map(tag -> new TagListResponse(tag.getId(), tag.getName()))
                .collect(Collectors.toList()));
    }
}
