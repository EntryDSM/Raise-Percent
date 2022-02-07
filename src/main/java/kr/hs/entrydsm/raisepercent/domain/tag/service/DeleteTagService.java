package kr.hs.entrydsm.raisepercent.domain.tag.service;

import kr.hs.entrydsm.raisepercent.domain.tag.domain.Tag;
import kr.hs.entrydsm.raisepercent.domain.tag.domain.repositories.TagRepository;
import kr.hs.entrydsm.raisepercent.domain.tag.exception.TagNotFoundException;
import kr.hs.entrydsm.raisepercent.global.util.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteTagService {

    private final TagRepository tagRepository;

    public void execute(String tagId) {
        Tag tag = tagRepository.findById(UUIDUtil.convertToUUID(tagId))
                .orElseThrow(() -> TagNotFoundException.EXCEPTION);

        tagRepository.delete(tag);
    }
}
