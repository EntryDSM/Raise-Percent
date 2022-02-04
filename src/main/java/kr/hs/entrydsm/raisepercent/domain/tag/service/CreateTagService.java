package kr.hs.entrydsm.raisepercent.domain.tag.service;

import kr.hs.entrydsm.raisepercent.domain.tag.domain.Tag;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.TagRepository;
import kr.hs.entrydsm.raisepercent.domain.tag.exception.AlreadyRegisteredTagException;
import kr.hs.entrydsm.raisepercent.domain.tag.presentation.dto.request.CreateTagRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateTagService {

    private final TagRepository tagRepository;

    public void execute(CreateTagRequest request) {
        if (tagRepository.findByName(request.getName()).isPresent()) {
            throw AlreadyRegisteredTagException.EXCEPTION;
        }

        tagRepository.save(Tag.builder()
                .name(request.getName())
                .build());
    }
}
